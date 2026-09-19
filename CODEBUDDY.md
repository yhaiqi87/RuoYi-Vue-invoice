# CODEBUDDY.md

This file provides guidance to CodeBuddy Code when working with code in this repository.

## 仓库结构

本文件位于后端模块 `RuoYi-invoice/`（即本目录）。同仓库（上一级 `yhq-invoice/`）由三个平级部分组成：

- `RuoYi-invoice/`（ **本目录**）—— 后端，Spring Boot 3.5.16 + RuoYi 3.9.2，Java 17，Maven 多模块。 **所有 `mvn`
  命令都在本目录执行。**
- `../RuoYi-invoice-web-Vue3-ts` —— 前端，Vue 3 + Vite + TypeScript + Element Plus + Pinia。
- 乐企能力说明文档：`doc/乐企接口文档/`（位于本目录内，见底部「文档」）。

后端 Maven `groupId` 统一为 `com.ruoyi`；发票相关模块的根 Java 包为 `com.luoge.ns.invoice`。

## 业务概述

后端封装「乐企（税务局能力开放平台）」与「RPA」两个渠道的开票、查验、入账、归集、抵扣勾选、退税勾选能力，对外以 **6 类独立能力域**
统一暴露。业务字段与接口编码的权威来源是 `doc/乐企接口文档` 下的乐企能力说明文档。

## 构建与运行命令

后端（在 **本目录（RuoYi-invoice）** 下执行， **本仓库没有 Maven Wrapper，使用系统 `mvn`**）：

- 构建全部模块：`mvn clean package`
- 启动应用（主类 `com.luoge.ns.invoice.InvoiceApplication`，默认端口 8080）：
  `mvn -pl invoice-app spring-boot:run`
  或打包后 `java -jar invoice-app/target/invoice-app-3.9.2.jar`
- 运行全部测试：`mvn test`
- 运行单模块测试：`mvn -pl invoice-app test`
- 运行单个测试类/方法：`mvn test -Dtest=InvoiceFacadeTest -pl invoice-app`
- 编译单个模块：`mvn -pl invoice-common compile`

> **测试坑**：根 `pom.xml` 显式锁定 `maven-surefire-plugin` 为 `3.5.6`。Maven 3.8 自带的 surefire 2.12.4 不识别 JUnit
> 5，会导致 `invoice-*` 模块的单元测试被静默跳过。不要回退该插件版本。当前已有测试：
> `invoice-app/.../facade/InvoiceFacadeTest`、`invoice-banstyle/.../BanstyleGeneratorTest`、
> `invoice-banstyle/.../HtmlBanstyleGeneratorTest`。

前端（在 `../RuoYi-invoice-web-Vue3-ts` 下，使用 npm）：

- 安装依赖：`npm install`（目录已含 `node_modules` 与 `package-lock.json`）
- 开发：`npm run dev`
- 生产构建：`npm run build:prod`（或 `npm run build:stage`）
- 预览：`npm run preview`
- 前端 `package.json` 未定义 test 脚本。

运行前置条件（RuoYi 标准）：需要 **MySQL + Redis**。初始化 SQL 在 `sql`（`ry_20260417.sql`、`quartz.sql`）；数据源 / Redis 配置在
`ruoyi-admin/src/main/resources/application*.properties`。

## 模块划分

`pom.xml` 聚合 11 个模块：6 个 RuoYi 标准模块（`ruoyi-admin` / `ruoyi-framework` / `ruoyi-system` / `ruoyi-quartz` /
`ruoyi-generator` / `ruoyi-common`）与 5 个发票模块：

- `invoice-common`：跨渠道公共契约与资产。定义 6 套统一能力接口（`InvoiceCapabilityService` / `GjCapabilityService` /
  `GxCapabilityService` / `TsCapabilityService` / `CyCapabilityService` / `RzCapabilityService`）、请求基类
  `InvoiceRequest`、统一响应 `ChannelResponse<T>`、异常 `InvoiceException`、全部 Req/Res DTO、枚举（`Channel` /
  `CapabilityCode` / 各能力接口编码 / `LeqiReturnCode`）、自定义校验 `@BigDecimalRange` 与 HTTP 抽象 `InvoiceHttpClient`。
- `invoice-app`：Spring Boot 启动入口（`InvoiceApplication`）+ Web 层。6 个 Controller 与 6 个门面，以及门面兜底切面
  `InvoiceFacadeAspect`。
- `invoice-leqi`：乐企渠道实现（见架构）。
- `invoice-rpa`：RPA 渠道实现（多数方法当前抛 `UnsupportedOperationException` 占位）。
- `invoice-banstyle`：数电票版式文件（PDF/HTML）生成，独立模块， **不经渠道路由**（直接被其他模块调用生成版式，不注册进
  `Channel` 路由表）。

## 核心架构：两层路由 + 统一契约

请求路径为 `Controller → Facade → 渠道服务 → 能力服务`，每能力域一一对应一套 Controller/Facade：

1. **渠道路由（Channel）**：端点形如 `POST /{invoice|gj|gx|ts|cy|rz}/{action}/{channel}`，`{channel}` 是 `Channel`
   枚举（LEQI / RPA）。各门面在构造时把对应渠道实现收进 `Map<Channel, XxxCapabilityService>`，按 channel 选出后委托同签名方法。门面
   **只做渠道选择**，不拆分业务能力。
2. **能力路由（CapabilityCode，仅开票能力）**：`LeqiInvoiceService` 内部再做一层路由。它把所有 `LeqiCapabilityService`
   实现（Spring 自动注入的 `List`）收进 `Map<CapabilityCode, LeqiCapabilityService>`，按 `req.getCapabilityCode()`
   选出并委托。新增开票能力：新建 `LeqiInvoiceXxxService` 实现 `LeqiCapabilityService` 即可自动注册。
3. **查验/入账/归集/抵扣勾选/退税勾选无 CapabilityCode 路由**：各自渠道服务（`LeqiCyService` / `LeqiRzService` /
   `LeqiGjService` / `LeqiGxService` / `LeqiTsService`，以及对应 RPA 实现）直接实现各自契约，由对应 Facade 按 Channel 选出，不经
   `LeqiInvoiceService`，也不注册进 `CapabilityCode` 路由表。
4. **统一契约**：`invoice-common` 中 6 套 `XxxCapabilityService` 接口定义各能力域的全部业务方法。新增业务动作：先在 common
   加 Req/Res + 接口方法，再分别在 leqi 与 rpa 实现类补齐（rpa 暂可抛 `UnsupportedOperationException`）。

**6 个能力域**：`invoice`(开票) / `gj`(归集) / `gx`(抵扣勾选) / `ts`(退税勾选) / `cy`(发票查验) / `rz`(发票入账)。其中
`cy` 与 `rz` 是较新加入、被原版说明遗漏的能力域，其契约在 `CyCapabilityService` / `RzCapabilityService`，渠道实现为
`LeqiCyService`/`RpaCyService` 与 `LeqiRzService`/`RpaRzService`。

**开票能力的 10 个 CapabilityCode**（`CapabilityCode` 枚举，`leqiCode` 即乐企 nlbm 请求头）：
BASE (202007)、SALE (202046)、LEASE (202038)、BUILD (202044)、FREIGHT (202026)、JRSP (202086)、ESC (202082 二手车)、FX (202083
反向开票)、BARCODE (202080 商品条码)、CPY (202055 成品油)。对应 `LeqiInvoiceBaseService` / `Bdcxs`(SALE) / `Bdczl`(LEASE) /
`Jzfw`(BUILD) / `Hwys`(FREIGHT) / `Jrsp`(JRSP) / `Esc` / `Fx` / `Barcode` / `Cpy` 十个实现。

## 关键约定（来自代码，务必遵守）

- **请求 DTO 继承 `InvoiceRequest`**；`capabilityCode` 字段带 `@JsonIgnore`，仅用于开票能力内部路由，
  **不作为乐企报文上送**。能力差异用扩展子类承载。
- **统一响应一律用 `ChannelResponse.of(code, msg, data)`**，不要直接抛异常穿透到 Controller。`InvoiceFacadeAspect` 对
  **开票门面**做异常兜底与 null 兜底，业务异常统一为 `InvoiceException`；其他门面沿用相同的 `ChannelResponse`
  返回风格，但需自行保证不抛穿透异常。
- **乐企接口编码（fwbm 请求头）由各能力 `XxxInterfaceCode` 枚举提供**（Base/Sale/Lease… 各一套）；开票能力编码（nlbm 请求头）由
  `CapabilityCode.leqiCode` 提供；归集 (203067)/抵扣勾选 (203065)/退税勾选 (203064)及各接口 fwbm 由各域 DTO javadoc 标注。
- **入参校验**：在能力服务类上标注 `@ValidateReq`，切面 `RequestValidateAspect` 会自动对所有 `InvoiceRequest` 入参跑
  jakarta.validation；DTO 字段用 `@NotBlank/@Size/@NotNull/@BigDecimalRange` 声明约束（约束值来自 doc 文档）。
  `@BigDecimalRange` 仅校验有值时的范围，空值交给 `@NotNull`。
- **乐企真实调用链路集中在 `LeqiHttpUtil.invoke()`**：JSON 化 → SM4 加密（占位透传）→ 组装请求头 → POST 网关 →
  gzip/明文响应解析 → 外层 `KfptRes` / 内层 `ServerResponse` 信封解析 → data 解密 → 映射业务 Res。`SM4Util`
  、网关地址、签名、平台编号等均为 **占位空值**，待真实接入；RPA 侧 `RpaHttpClient` 同样返回示例数据。

## 文档

`doc/乐企接口文档` 是字段、接口编码、返回码（returncode `00` 成功，个别接口 `01` =
未查询到发票信息）的权威依据。新增或修改任何开票 / 查验 / 入账 / 归集 / 勾选 DTO 或乐企接口前，应先核对对应文档：

- 开票 6 类：`乐企数字化电子发票（基础版）开票能力说明文档-V6.006`、`（不动产销售）-V3.004`、`（不动产经营租赁）-V3.021`、
  `（货物运输）-V3.008`、`（建筑服务）-V4.002`、`（金融商品转让）V1.005`，另含 `（二手车）-V1.003`、`（反向开票通用）-V1.002`、
  `（商品条码）-V1.005`、`（成品油）-V2.009`。
- 用票类：`乐企发票查验能力说明文档-V1.025`、`乐企发票入账能力说明文档-V1.004`、`乐企归集能力说明文档-V2.021`、
  `乐企增值税抵扣勾选能力说明文档-V3.025`、`乐企增值税退税勾选能力说明文档-V2.013`。
- 版式与平台：`数字化电子发票版式及XML规范V6.000`、`乐企数字开放平台-沙箱操作指引1/2`、`乐企监管公共能力说明文档`、
  `乐企使用单位管理能力说明文档`、`乐企消息通知查询接口说明文档`、`乐企长期资产进项税额抵扣台账报送能力说明文档`、
  `使用单位乐企授权认证流程操作指引`。
- 历史归档报文示例在 `doc/乐企接口文档/归档/`。

勾选 / 归集 / 查验 / 入账三类能力的字段依据文档已在对应 DTO 的 javadoc 中标注，但仓库 `乐企接口文档/` 外的 `.docx`
可能随版本变动，改动前请核对目录内最新版本号。

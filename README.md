# ds-doctor 项目介绍

## 一、项目概述

`ds-doctor `是一个基于 Java 的项目，使用 Maven 进行项目管理，开发环境为 JDK 21。该项目包含了多个模块，如实体类、Mapper 接口、服务类、控制器等，同时配置了日志、跨域等相关设置。

## 二、项目结构

1. 主要目录结构
  ```plaintext
  ds-doctor
  ├── .idea               # IntelliJ IDEA 项目配置文件
  ├── src
  │   ├── main
  │   │   ├── java        # Java 源代码
  │   │   │   └── com
  │   │   │       └── hnz
  │   │   │           ├── aspect      # 切面类
  │   │   │           ├── config      # 配置类
  │   │   │           ├── controller  # 控制器类
  │   │   │           ├── entity      # 实体类
  │   │   │           ├── mapper      # Mapper 接口
  │   │   │           ├── service     # 服务类
  │   │   │           └── utils       # 工具类
  │   │   └── resources   # 资源文件
  │   │       ├── mapper  # MyBatis Mapper XML 文件
  │   │       └── application-dev.yml # 开发环境配置文件
  │   └── test
  │       └── java        # 测试代码
  │           └── com
  │               └── hnz
  └── pom.xml             # Maven 项目配置文件
  ```


2. 主要文件说明
    `pom.xml`：Maven 项目配置文件，定义了项目的基本信息、依赖和插件等。
    `src/main/java/com/hnz/AppApplication.java`：项目的入口类，包含 main 方法。
    `src/main/resources/application-dev.yml`：开发环境的配置文件，设置了服务器端口、应用名称和日志级别等。
    `src/main/resources/mapper/ChatRecordMapper.xml`：MyBatis 的 Mapper XML 文件，定义了 ChatRecord 实体类的查询映射结果。

  ## 三、开发环境要求

  **JDK**：21
  **Maven**：建议使用 3.9 及以上版本

  ## 四、运行步骤
1. 克隆项目
  ```bash
  git clone <项目仓库地址>
  cd ds-doctor
  ```


2. 配置 Maven
    确保你的 Maven 配置文件 `settings.xml` 中配置了正确的镜像和仓库地址。在 `ds-doctor/.idea/workspace.xml` 中可以看到项目使用的 Maven 配置信息：

```xml
<component name="MavenImportPreferences">
    <option name="generalSettings">
        <MavenGeneralSettings>
            <option name="customMavenHome" value="E:\code_profiles\maven-3.9" />
            <option name="localRepository" value="E:\code_profiles\maven-3.9\repository" />
            <option name="mavenHomeTypeForPersistence" value="CUSTOM" />
            <option name="userSettingsFile" value="E:\code_profiles\maven-3.9\conf\settings.xml" />
        </MavenGeneralSettings>
    </option>
</component>
```

3. 构建项目
  ```bash
  mvn clean install
  ```

4. 运行项目
在 IDE 中打开` src/main/java/com/hnz/AppApplication.java `文件，运行 main 方法；或者使用以下命令：

```bash
java -jar target/ds-doctor-1.0-SNAPSHOT.jar
```

## 五、注意事项

项目中使用了一些 IDE 特定的配置文件（如 .idea 目录下的文件），如果你使用的是不同的 IDE，可能需要进行相应的调整。
**项目中的代码目前仅为示例代码。**

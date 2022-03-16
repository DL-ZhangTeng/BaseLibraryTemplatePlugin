# BaseLibraryTemplatePlugin

## AndroidStudio 4.X+ 编写自定义模板

* AndroidStudio 自定义模板主要区分在AS 4.0版本
  * AndroidStudio 4.0版本之前，编写自定义模板 使用`FreeMarket`进行编写，并存放在 AS 的plugin - template 文件夹下即可。可参考 鸿洋 文章 [AndroidStudio自定义模板](https://mp.weixin.qq.com/s/doXNWf_TAB-ZlaHVkrUgzQ) 进行学习。
  * AndroidStudio 4.0版本之后，AS 没有了 plugin 文件夹，但提供了新的方式：使用`Kotlin` 编写 template，以`jar`包形式使用。

### 开始
---
#### Use this template

我们需要在官方的template模板上进行编写，官方模板地址为:[intellij-platform-plugin-template](https://github.com/JetBrains/intellij-platform-plugin-template),打开模板仓库后，点击`Use this template` 会提示你 `Create a new repository from intellij-platform-plugin-template` 类似`fork`，创建好仓库后，git clone 到本地，并使用AS 打开

#### 添加依赖

* 添加wizard-template.jar
  * 项目根目录下创建`lib`文件夹
  * 添加AndroidStudio目录下的`wizard-template.jar`,具体路径为:`/Applications/Android Studio.app/Contents/plugins/android/lib/`
  * 打开项目`build.gradle.kts`文件，添加代码如下：
```
dependencies {
    compileOnly(files("lib/wizard-template.jar"))
}
```
* 添加plugins
  * 打开项目`build.gradle.kts`文件，在`plugins`下添加`detekt` 和 `ktlint`插件
  * 添加`detekt`依赖代码如下：

```
    plugins {
    ...
    // detekt linter - read more: https://detekt.github.io/detekt/gradle.html
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    // ktlint linter - read more: https://github.com/JLLeitschuh/ktlint-gradle
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}


dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
}
```
* 添加过依赖后`build.gradle.kts` 下`plugins` 和`dependencies`代码：

```
plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "1.3.1"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "0.1.13"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.3.0"
    // detekt linter - read more: https://detekt.github.io/detekt/gradle.html
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    // ktlint linter - read more: https://github.com/JLLeitschuh/ktlint-gradle
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}


dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
    compileOnly(files("lib/wizard-template.jar"))
}
```

#### 修改gradle.properties

* 修改`gradle.properties`文件下相关配置 具体意义查看[Gradle properties](https://github.com/JetBrains/intellij-platform-plugin-template#gradle-properties)
  * pluginGroup
  * pluginName
  * pluginVersion : 编译后生成jar版本号就是这里控制的
  * pluginSinceBuild
  * pluginUntilBuild
  * pluginVerifierIdeVersions
  * platformType
  * platformVersion
  * platformPlugins
* 此处需注意的是 `pluginSinceBuild` `pluginUntilBuild` `pluginVerifierIdeVersions`
  * pluginSinceBuild:表示插件适配的最低版本
  * pluginUntilBuild:表示插件适配的最高版本
  * pluginVerifierIdeVersions: 4.X版本AS 使用模板会有此字段，Fox版本没有，文档也没有显示，个人还是添加上了
  * 关于最低 最高版本 就是AS 版本信息中build 信息，本人使用的是AndroidStudio Fox 2020.3.1 Patch 3 Build，build 信息为203.xxxx

![picture1](https://github.com/DL-ZhangTeng/BaseLibraryTemplatePlugin/blob/main/picture/1.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2R1b2x1bzk=,size_16,color_FFFFFF,t_70#pic_center)

* `gradle.properties`文件内容如下：

```
# IntelliJ Platform Artifacts Repositories
# -> https://plugins.jetbrains.com/docs/intellij/intellij-artifacts.html

pluginGroup = com.github.dlzhangteng.baselibrarytemplateplugin
pluginName = BaseLibraryTemplatePlugin
# SemVer format -> https://semver.org
pluginVersion = 1.3.1

# See https://plugins.jetbrains.com/docs/intellij/build-number-ranges.html
# for insight into build numbers and IntelliJ Platform versions.
pluginSinceBuild = 203
pluginUntilBuild = 213.*

# IntelliJ Platform Properties -> https://github.com/JetBrains/gradle-intellij-plugin#intellij-platform-properties
platformType = IC
platformVersion = 2020.3.4

# Plugin Dependencies -> https://plugins.jetbrains.com/docs/intellij/plugin-dependencies.html
# Example: platformPlugins = com.intellij.java, com.jetbrains.php:203.4449.22
platformPlugins =

# Java language level used to compile sources and to generate the files for - Java 11 is required since 2020.3
javaVersion = 11

# Gradle Releases -> https://github.com/gradle/gradle/releases
gradleVersion = 7.3

# Opt-out flag for bundling Kotlin standard library.
# See https://plugins.jetbrains.com/docs/intellij/kotlin.html#kotlin-standard-library for details.
# suppress inspection "UnusedProperty"
kotlin.stdlib.default.dependency = false

```

#### 修改包名以及创建Template生成类

* 修改项目包名
  * AS 打开项目后，可以看到src - kotlin - 包名 - listeners services ...
  * 修改包名，个人修改结果如下
  * 具体为src - kotlin - 包名 com.github.dlzhangteng.baselibrarytemplateplugin - 三个文件夹，两个存放 项目原本文件 listeners services，一个存放自定义模板代码 template

![picture2](https://github.com/DL-ZhangTeng/BaseLibraryTemplatePlugin/blob/main/picture/2.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2R1b2x1bzk=,size_16,color_FFFFFF,t_70#pic_center)

* 修改MyProjectManagerListener


```
internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        projectInstance = project
        project.getService(MyProjectService::class.java)
    }

    override fun projectClosing(project: Project) {
        projectInstance = null
        super.projectClosing(project)
    }

    companion object {
        var projectInstance: Project? = null
    }
}
```

* 创建Template入口生成类 SamplePluginTemplateProviderImpl
  * template文件夹下创建 `SamplePluginTemplateProviderImpl`类
  * `WizardTemplateProvider`的是 lib 下 `wizard-template.jar`的抽象类
  * `baseActivityTemplate.kt`为具体生成Template对象

```
class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        baseActivityTemplate
    )
}
```
* 修改plugin.xml
  * 创建好`SamplePluginTemplateProviderImpl`类后，打开`resources-META-INF-plugin.xml`文件
  * 修改id
  * 修改name：此处名字是AS plugin安装后显示的名字
  * 修改vendor:此处为AS plugin 安装后显示的作者名字
  * 添加三个 depends
  * 添加extensions 指定`defaultExtensionNs` 为 `com.android.tools.idea.wizard.template`，`wizardTemplateProvider` 为创建的`SamplePluginTemplateProviderImpl`类
  * 此处注意，一定要写全路径，包名一定一定一定要写上,4.X版本编写的时候 网上的文章都没有写，个人不清楚4.X版本是否真的不用写全路径


```
<idea-plugin>
    <id>com.github.dlzhangteng.baselibrarytemplateplugin</id>
    <name>BaseLibraryTemplatePlugin</name>
    <vendor>dl-zhangteng</vendor>

    <depends>org.jetbrains.android</depends>
    <depends>org.jetbrains.kotlin</depends>
    <depends>com.intellij.modules.platform</depends>

    <extensions defaultExtensionNs="com.intellij">
        <applicationService
            serviceImplementation="com.github.dlzhangteng.baselibrarytemplateplugin.services.MyApplicationService" />
        <projectService
            serviceImplementation="com.github.dlzhangteng.baselibrarytemplateplugin.services.MyProjectService" />
    </extensions>

    <applicationListeners>
        <listener
            class="com.github.dlzhangteng.baselibrarytemplateplugin.listeners.MyProjectManagerListener"
            topic="com.intellij.openapi.project.ProjectManagerListener" />
    </applicationListeners>
    <extensions defaultExtensionNs="com.android.tools.idea.wizard.template">
        <wizardTemplateProvider
            implementation="com.github.dlzhangteng.baselibrarytemplateplugin.template.SamplePluginTemplateProviderImpl" />
    </extensions>
</idea-plugin>
```




* 创建Template具体对象`baseActivityTemplate`
  * template 文件夹下创建了`activity/base`文件夹，并在其下创建名为`baseActivityTemplate`的kt文件
  * 字段含义
    * revision ：低版本的时候用于说明版本，此次fox编译的时候 revision会报错，遂注释
    * name : 编译好Jar包使用时，显示的模板名字
    * description : 使用模板时，顶部简介
    * minApi : 低版本4.0以下编写的时候 使用的min_api minBuildApi，用于说明编译版本，此次fox编写时，minBuildApi会报错，遂也改minApi ,MIN_API为lib 下 `wizard-template.jar`中字段
    * category : 定义为other
    * formFactor : 定义为 mobile
    * screens : 枚举WizardUiContext，表示模板在何处显示；NewProject：新建项目时网格显示Template按钮，NewModule：新建Module时网格显示Template按钮，ActivityGallery：新建Activity时网格显示Template按钮，FragmentGallery：新建Fragment时网格显示Template按钮，MenuEntry：右键新建Activity菜单中显示Template按钮。
    * name：界面上的类似label的提示语,constraints：填写值的约束,suggest：建议值，比如填写ActivityName的时候，会给出一个布局文件的建议值,default:默认值,help:底部显示的提示语
    * widgets:将编写的Template对象放入。
    * recipe :设置具体替换对象。此处在`activity`文件夹下创建`baseActivityRecipe`的kt文件，将需要的值通过参数传入
```

val baseActivityTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseActivity"
        description = "一键创建 BaseActivity "
        minApi = MIN_API
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val mActivityPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Activity 包名"
        }

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help = "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mActivityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { mIsGenerateActivityLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { activityToLayout(mPageName.value) }
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mActivityLayoutName),
            CheckBoxWidget(mIsGenerateActivityLayout),
            PackageNameWidget(mActivityPackageName),
        )

        recipe = { data: TemplateData ->
            baseActivityRecipe(
                data as ModuleTemplateData,
                mPageName.value,
                mActivityLayoutName.value,
                mIsGenerateActivityLayout.value,
                mActivityPackageName.value
            )
        }

    }

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: ArrayList<String> =
        com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.splitByUpperCase(
            pageName
        )
    activityChildNames.forEach {
        stringBuilder.append("_").append(it.toLowerCase())
    }
    return stringBuilder.toString()
}

/**
 * 根据大写字母拆分数组
 */
fun splitByUpperCase(str: String): ArrayList<String> {
    val rs = ArrayList<String>()
    var index = 0
    val len = str.length
    for (i in 1 until len) {
        if (Character.isUpperCase(str[i])) {
            rs.add(str.substring(index, i))
            index = i
        }
    }
    rs.add(str.substring(index, len))
    return rs
}

baseActivityRecipe.kt :

fun RecipeExecutor.baseActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,
) {
    generateManifest(
        moduleData = moduleTemplateData,
        activityClass = "${mPageName}Activity",
        packageName = mActivityPackageName,
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = false
    )
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
            .replace(".", "")
    val rootPath =
        if (!packageNameStr.isNullOrEmpty()) mActivityPackageName.replace(".$packageNameStr", "")
        else mActivityPackageName
    val baseActivity = baseActivity(
        rootPath,
        packageNameStr,
        mPageName
    )
    // 保存Activity

    save(
        baseActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml"))
    }
}

baseActivity.kt:

fun baseActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle

import com.zhangteng.base.base.BaseActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${
    com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName(
        mPageName
    )
})
    }

    override fun initView() {

    }

    override fun initData() {

    }
}
"""
```

#### 编译
编写好后 点击 Gradle - Task - Build - jar，编译好后 会在 项目根目录-build-libs文件夹下生成jar包，名字为 项目名-版本号.jar

可能你的Gradle 页面是这样的:

![picture3](https://github.com/DL-ZhangTeng/BaseLibraryTemplatePlugin/blob/main/picture/3.webp?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2R1b2x1bzk=,size_16,color_FFFFFF,t_70#pic_center)

点击`Task list not built..`

去掉`Gradle` 下第一个 `Do not build Gradle task list ..`的勾选

拿着编译好的jar包，打开AS `Settings/Preferences` > `Plugins` > `⚙️` > `Install plugin from disk...` > `RESTART IDE`
### 鸣谢

[JiaYang627](https://github.com/JiaYang627) : [TinMVVM](https://github.com/JiaYang627/TinMVVM/blob/main/README.md)

[鸿洋](https://blog.csdn.net/lmj623565791?spm=1001.2014.3001.5509) : [Android Studio自定义模板 写页面竟然可以如此轻松](https://blog.csdn.net/lmj623565791/article/details/51635533)

![Build](https://github.com/DL-ZhangTeng/BaseLibraryTemplatePlugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "BaseLibraryTemplatePlugin"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/DL-ZhangTeng/BaseLibraryTemplatePlugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

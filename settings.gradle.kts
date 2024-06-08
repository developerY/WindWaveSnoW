pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "windwatersnow"

include(":app:mobile")
include(":app:wear")
include(":data")
include(":core:ui")
include(":core:util")
include(":feature:wear:home")
include(":feature:wear")


/*include(":app:mobile")
include(":app:wear")
include(":core:lib")
include(":core:testing")
include(":core:ui")
include(":core:util")
include(":data")
include(":dynamic")
include(":feature:details")
include(":feature:list")
include(":feature:photo")
include(":feature:wear:home")
include(":test:navigation")

include(":feature:audio")
include(":feature:cal")
include(":feature:cat")*/




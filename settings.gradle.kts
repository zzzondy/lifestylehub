pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LifestyleHub"
include(":app")

include(":common:ui")
include(":common:navigation")
include(":common:network")
include(":common:planner_feature_data")


include(":features:main:presentation")
include(":features:main:domain")
include(":features:main:data")

include(":features:planner:presentation")
include(":features:planner:domain")
include(":features:planner:data")

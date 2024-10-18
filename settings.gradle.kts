pluginManagement {
    includeBuild("convention-plugins/project")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

rootProject.name = "CashTracker"

include(":composeApp")
include(":desktopApp")
include(":umbrella")

include(":core:database")
include(":core:network")
include(":core:mediator")
include(":core:utils")
include(":core:compose")

include(":feature:main:data")
include(":feature:main:domain")
include(":feature:main:presentation")

include(":feature:budget:data")
include(":feature:budget:domain")
include(":feature:budget:presentation")

include(":feature:transactions:data")
include(":feature:transactions:domain")
include(":feature:transactions:presentation")

include(":feature:settings:data")
include(":feature:settings:domain")
include(":feature:settings:presentation")

include(":feature:accounts:data")
include(":feature:accounts:domain")
include(":feature:accounts:presentation")

include(":feature:goals:data")
include(":feature:goals:domain")
include(":feature:goals:presentation")

include(":feature:cashback:data")
include(":feature:cashback:domain")
include(":feature:cashback:presentation")

include(":feature:currency:domain:api")

include("feature:transaction:data")
include("feature:transaction:domain")
include("feature:transaction:domain:api")
include("feature:transaction:presentation")
include("feature:transaction:presentation:api")

include("feature:account:data")
include("feature:account:domain")
include("feature:account:domain:api")
include("feature:account:presentation")
include("feature:account:presentation:api")

include("feature:category:data")
include("feature:category:domain")
include("feature:category:domain:api")
include("feature:category:presentation")
include("feature:category:presentation:api")

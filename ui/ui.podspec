Pod::Spec.new do |spec|
    spec.name                     = 'ui'
    spec.version                  = '0.6.0-SNAPSHOT'
    spec.homepage                 = 'https://github.com/vanniktech/ui'
    spec.source                   = { :http=> ''}
    spec.authors                  = 'Niklas Baudy'
    spec.license                  = 'MIT'
    spec.summary                  = 'Kotlin Multiplatform UI goodies'
    spec.vendored_frameworks      = 'build/cocoapods/framework/ui.framework'
    spec.libraries                = 'c++'
                
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':ui',
        'PRODUCT_MODULE_NAME' => 'ui',
    }
                
    spec.script_phases = [
        {
            :name => 'Build ui',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end
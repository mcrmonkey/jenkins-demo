freeStyleJob('setup-seed') {
    displayName('seed-dsl')
    description('Applies all Jenkins DSLs from the repo.')

    checkoutRetryCount(3)

    logRotator {
        numToKeep(100)
        daysToKeep(15)
    }

    scm {
        git {
            remote {
                url('https://github.com/mcrmonkey/jenkins-dsl.git')
            }
            branches('*/main')
            extensions {
                wipeOutWorkspace()
                cleanAfterCheckout()
            }
        }
    }

    triggers {
    }

    wrappers { colorizeOutput() }

    steps {
        dsl {
            external('**/*.groovy')
            removeAction('DELETE')
            removeViewAction('DELETE')
            additionalClasspath('.')
        }
    }

    publishers {
        wsCleanup()
    }
}


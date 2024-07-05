#!groovy

import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import jenkins.model.Jenkins

// Create the configuration pipeline from a jobDSL script
def jobDslScript = new File('/usr/share/jenkins/ref/jobs/seed.groovy')
def workspace = new File('.')
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(jobDslScript.text)


def jobName = 'setup-seed'

def job = Jenkins.instance.getJob(jobName)

if (job) {
  job.scheduleBuild()
  println "Job '${jobName}' triggered successfully."
} else {
  println "Job '${jobName}' not found."
}


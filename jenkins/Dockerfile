FROM jenkins/jenkins:2.466


ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG=/usr/share/jenkins/ref/casc

ENV ARCH=linux_amd64 \
    TERRAFORM_VERSION=1.9.1


USER root

RUN apt-get update -qq && apt-get install -qq make

# Terraform client
RUN curl -sL https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_${ARCH}.zip -o /tmp/terraform_${TERRAFORM_VERSION}_${ARCH}.zip \
  && curl -sL https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_SHA256SUMS -o /tmp/terraform_${TERRAFORM_VERSION}_SHA256SUMS \
  && cd /tmp && sha256sum -c terraform_${TERRAFORM_VERSION}_SHA256SUMS 2>&1 | grep OK \
  && unzip /tmp/terraform_${TERRAFORM_VERSION}_${ARCH}.zip -d /bin \
  && rm -f /tmp/terraform_${TERRAFORM_VERSION}_*


USER jenkins

RUN mkdir -p /usr/share/jenkins/ref/{casc,jobs}

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN /bin/jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt


ADD casc_*.yaml /usr/share/jenkins/ref/casc/

COPY startup.groovy /usr/share/jenkins/ref/init.groovy.d/startup.groovy
COPY seed.groovy /usr/share/jenkins/ref/jobs/seed.groovy

These variables should be exported before running tests: ANDROID_HOME, ANDROID_SDK_ROOT, JAVA_HOME.
Example for Linux environment;

    echo export ANDROID_HOME=~/Android/Sdk >> ~/.bashrc
    echo export ANDROID_SDK_ROOT=~/Android/Sdk/ >> ~/.bashrc
    echo export JAVA_HOME=/usr/lib/jvm/default-java/ >> ~/.bashrc
    echo export PATH=$PATH:$JAVA_HOME/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/ >> ~/.bashrc
    source ~/.bashrc

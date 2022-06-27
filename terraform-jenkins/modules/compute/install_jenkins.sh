sudo yum update -y
sudo amazon-linux-extras enable corretto8
sudo yum install java-1.8.0-amazon-corretto
sudo yum install java-1.8.0-amazon-corretto-devel
java -version
sudo yum update -y
sudo yum install git -y
git version
mkdir mygit
cd mygit/
git init
cd ~
sudo yum update -y
sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo amazon-linux-extras install java-openjdk11 -y
sudo yum install jenkins -y
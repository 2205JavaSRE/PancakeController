provider "aws" {
    region = "us-east-1"
    access_key="AKIAR224AV4IPTZIW7TM"
    secret_key = "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"
}

#Provisioning a EC2 instance 
resource "aws_instance" "terraform_web_server" {
    
    ami = "ami-0cff7528ff583bf9a"
    instance_type = "t2.micro"
    availability_zone = "us-east-1"
    key_name = "2205key2"

    vpc_security_group_ids = [aws_security_group.terra_ec2_access.id]

    user_data = <<-EOF
                #!/bin/bash
                sudo yum update -y 
                sudo yum install git -y
                sudo yum install java-1.8.0-devel -y 
                EOF

    tags = {
      "name" = "terraform server"
    }
}
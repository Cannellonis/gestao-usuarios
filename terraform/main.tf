terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
  profile = "cannellonis"
}

resource "aws_s3_bucket" "s3-bucket" {
  bucket = "s3-bucket-cannellonis-0049"

  tags = {
    Name        = "s3-bucket"
    Environment = "Dev"
    ManagedBy   = "Terraform"
  }
}
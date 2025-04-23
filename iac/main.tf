terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  access_key                  = "test"
  secret_key                  = "test"
  region                      = "us-east-1"
  s3_use_path_style           = false
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true

  endpoints {
    apigateway     = "http://localhost:4566"
    apigatewayv2   = "http://localhost:4566"
    cloudformation = "http://localhost:4566"
    cloudwatch     = "http://localhost:4566"
    dynamodb       = "http://localhost:4566"
    ec2            = "http://localhost:4566"
    es             = "http://localhost:4566"
    elasticache    = "http://localhost:4566"
    firehose       = "http://localhost:4566"
    iam            = "http://localhost:4566"
    kinesis        = "http://localhost:4566"
    lambda         = "http://localhost:4566"
    rds            = "http://localhost:4566"
    redshift       = "http://localhost:4566"
    route53        = "http://localhost:4566"
    s3             = "http://s3.localhost.localstack.cloud:4566"
    secretsmanager = "http://localhost:4566"
    ses            = "http://localhost:4566"
    sns            = "http://localhost:4566"
    sqs            = "http://localhost:4566"
    ssm            = "http://localhost:4566"
    stepfunctions  = "http://localhost:4566"
    sts            = "http://localhost:4566"
  }
}

resource "aws_sns_topic" "book-topic" {
  name         = "book-topic"
  display_name = "Book Topic"

  #   delivery_policy = <<EOF
  # {
  #     "requestPolicy": {
  #       "headerContentType": "application/json"
  #     }
  # }
  # EOF

  tags = {
    Environment = "local"
  }
}

resource "aws_sqs_queue" "book-for-all-you-can-read-queue" {
  name = "book-for-all-you-can-read-queue"

  tags = {
    Environment = "local"
  }
}

resource "aws_sns_topic_subscription" "book-for-all-you-can-read-subscription" {
  topic_arn            = aws_sns_topic.book-topic.arn
  protocol             = "sqs"
  endpoint             = aws_sqs_queue.book-for-all-you-can-read-queue.arn
  raw_message_delivery = true
}
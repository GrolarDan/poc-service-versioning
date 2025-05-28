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

  filter_policy = jsonencode({
    eventVersion = ["v1"]
  })
}

resource "aws_sqs_queue" "book-for-another-world-queue" {
  name = "book-for-another-world-queue"

  tags = {
    Environment = "local"
  }
}

resource "aws_sns_topic_subscription" "book-for-another-world-subscription" {
  topic_arn            = aws_sns_topic.book-topic.arn
  protocol             = "sqs"
  endpoint             = aws_sqs_queue.book-for-another-world-queue.arn
  raw_message_delivery = true

  # This is not possible to combine message body and attributes in the same filter policy
  # The only way to do this is to use MessageAttributes filter policy scope and filter by genre in the application
  # filter_policy_scope = "MessageBody"
  # filter_policy = jsonencode({
  #   genre = ["Fanfiction", "Fantasy", "Fiction narrative", "Fiction in verse", "Metafiction", "Mythology", "Realistic fiction", "Science fiction"],
  #   eventVersion = ["v1"]
  # })
  filter_policy = jsonencode({
    eventVersion = ["v1"]
  })
}

resource "aws_sqs_queue" "test-queue" {
  name = "test-queue"

  tags = {
    Environment = "local"
  }
}

resource "aws_sns_topic_subscription" "test-subscription" {
  topic_arn            = aws_sns_topic.book-topic.arn
  protocol             = "sqs"
  endpoint             = aws_sqs_queue.test-queue.arn
  raw_message_delivery = true

  filter_policy = jsonencode({
    eventVersion = ["v1", "v2"]
  })
}
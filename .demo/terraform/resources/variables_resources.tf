variable "github_token" {
  type        = string
  sensitive   = true
  description = "GitHub Personal Access Token with Repository and Packages Access"
}

variable "github_repository" {
  type = object({
    owner = string
    repo  = string
  })
  nullable = false
}

variable "requestor_handle" {
  type        = string
  description = "The GitHub handle of the requestor"
  nullable    = false
}

variable "uuid" {
  type        = string
  description = "The UUID of the demo"
  nullable    = false
}

variable "communication_issue_number" {
  type        = number
  description = "The issue numberfor the communication issue"
  nullable    = true
}

variable "demo_config" {
  type        = string
  description = "The JSON encoded configuration data for the demo resources"
  nullable    = true
}

variable "demo_definition" {
  type        = string
  description = "The JSON encoded definition data for the demo resources"
  nullable    = true
}

variable "github_instance_type" {
  type        = string
  description = "The type of GitHub instance being targeted"
  nullable    = true
}

variable "github_instance_tenant_name" {
  type        = string
  description = "The name of the GitHub tenant instance, if relevant to the instance type, e.g. proxima"
  nullable    = true
  default     = ""
}

variable "github_instance_urls_json" {
  type        = string
  description = "The JSON encoded URLs for the targeted GitHub instance type"
  nullable    = true
}


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
  nullable = false
}

variable "uuid" {
  type        = string
  description = "The UUID of the demo"
  nullable = false
}

variable communication_issue_number {
  type        = number
  description = "The issue numberfor the communication issue"
  nullable    = true
}

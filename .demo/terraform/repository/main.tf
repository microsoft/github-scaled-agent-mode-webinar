terraform {
  required_version = ">=1.7"

  required_providers {
    github = {
      source  = "integrations/github"
      version = "6.2.2"
    }
  }
}


#
# backend is dynamically generated and injected by terragrunt
#


provider "github" {
  token = var.github_token
  owner = var.github_repository.owner
}

locals {
  default_branch = "main"
}


resource "github_repository" "repository" {
  name                   = var.github_repository.repo
  description            = "Copilot Agent Mode demo repository for @${var.requestor_handle}"
  visibility             = "private"
  has_issues             = true
  delete_branch_on_merge = true

  # This is required otherwise we do not get a default branch for other follow on usage
  # of the repository to operate.
  auto_init = true
}

resource "github_branch_default" "main" {
  repository = github_repository.repository.name
  branch     = local.default_branch
}


output "repository_url" {
  value       = github_repository.repository.html_url
  description = "The HTML URL for the newly created repository"
}

output "repository_full_name" {
  value       = github_repository.repository.full_name
  description = "The name for the newly created repository in 'owner/repo_name' form"
}

output "repository_id" {
  value       = github_repository.repository.repo_id
  description = "The ID for the newly created repository"
}

output "repository_default_branch" {
  value       = local.default_branch
  description = "The default branch for the newly created repository"
}

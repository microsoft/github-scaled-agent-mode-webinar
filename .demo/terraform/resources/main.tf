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
  decoded_demo_config = jsondecode(var.demo_config)
  decoded_demo_definition = jsondecode(var.demo_definition)
}

# Lookup the github repository that should have been created before this module run is called
data "github_repository" "repository" {
  full_name = "${var.github_repository.owner}/${var.github_repository.repo}"
}

resource "github_team" "repository_admins" {
  name                      = "${data.github_repository.repository.name}-admins"
  description               = "Admins for the ${data.github_repository.repository.name} repository"
  create_default_maintainer = true
}

resource "github_team_repository" "repository_admins" {
  team_id = github_team.repository_admins.id
  repository = data.github_repository.repository.name
  permission = "admin"
}

resource "github_team_membership" "actor_repository_admin" {
  team_id = github_team.repository_admins.id
  username = var.requestor_handle
  role = "maintainer"
}

resource "github_branch_protection" "protect_default_branch" {
  repository_id  = "${data.github_repository.repository.name}"
  pattern        = "${data.github_repository.repository.default_branch}"
  enforce_admins = false

  required_pull_request_reviews {
    dismiss_stale_reviews           = true
    required_approving_review_count = 1
  }
}

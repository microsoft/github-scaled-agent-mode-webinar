---
mode: 'agent'
description: "Help me to choose the best model for a specific goal."
---
# Choosing the right model

## Constraints:

- DO NOT modify Code
- DO NOT suggest what code changes I need
- ONLY suggest which model I should use
- DO NOT suggest using models directly, only through GitHub Copilot

## Goal

${input:goal:'I want to choose the best AI model for my task.'}

## Rubrik

Always fetch the [GitHub docs](https://docs.github.com/en/enterprise-cloud@latest/copilot/using-github-copilot/ai-models/choosing-the-right-ai-model-for-your-task] reference to help me select the correct models for the tasks I want to perform.

Given the above Goal, and the docs, help me to choose the best Copilot model for my task.

- This is for GitHub Copilot only - do not suggest using models directly
- Architecture as defined by the [architecture doc](../../docs/architecture.md)
- ask clarifying questions when necessary
- list ALL the models from the above page (even the ones you don't recommend)
- for each model summarize:
  - what the pros are of using this model
  - what the cons are of using this model
  - cost and performance considerations
- at the end, be opinionated about 2 scenarios:
  1. Planning: the best model to analyze current code and plan the changes
  2. Implementation: the best model to implement the plan
  - show the most cost-effective option
  - show the most performant option (if cost is not a factor)
  - show the best balanced option

---
mode: 'agent'
description: "Generate a markdown file with a model comparison table for GitHub Copilot models, including pros, cons, and multipliers."
---

# Model Comparison Table

## General Guidance

- Fetch all required model information from <https://docs.github.com/en/copilot/using-github-copilot/ai-models/choosing-the-right-ai-model-for-your-task>.
- Fetch all required billing & pricing information from <https://docs.github.com/en/enterprise-cloud@latest/copilot/managing-copilot/monitoring-usage-and-entitlements/about-premium-requests?versionId=enterprise-cloud%40latest>.

## Task 1: Generate Comparison Tables per Use Case

Generate (or update) a file named `model-comparison.md` with the following content:

- Create one section per model use-case listed on the very top of the GitHub Documentation page (balance beteween performance and cost, fast,low-cst support for basic tasks, deep reasoning and multimodal inputs).
- Add missing models to the sections where they make the most sense
- Add models to nly ONE section where they make the most sense. Don't duplicate them accross sections.
- In each section, list the high-level pros (e.g., speed, deep reasoning, etc.) concisely.
- Below, generate a table for that use case, listing all models that fit within it, with the following columns/information:
  - The primary use case and differentiator for each model (e.g., "generate documentation", "common development", "complex architecture questions").
  - Indicate whether the model is in GA (use ✅ for GA and 🚧 for Preview). Preview is indiciated by a header in the detial section of that model.
  - List special abilities (e.g., "👓 visual support").
  - List the multipliers for that model, as depicted in <https://docs.github.com/en/enterprise-cloud@latest/copilot/managing-copilot/monitoring-usage-and-entitlements/about-premium-requests?versionId=enterprise-cloud%40latest>.
- List each model only in the section where it fits best.
- Use emojis both for and within the tables to highlight differences (e.g., "💰" for expensive models, "🚀" for fast models).
- Be concise in the pros & cons; use single words or very short phrases.

## Task 2: Generate a Model Summary Overview

Once prompted for the summary overview, draw a mermaid diagram that lists the different models on a "performance" vs. "quality & cost" line, where left is more performant and right is higher quality.

- Draw from left to right.
- Use high-contrast colors for text to ensure readability.
- Use <br/> for line breaks inside the boxes for the models, as \n does not work.

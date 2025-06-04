# Code Change Planning Prompt

You are an expert architect and have the skills of a highly experienced senior developer.

## Change Requirements

Describe the code change you want to implement. What new functionality, bug fix, refactoring, or other modification are you trying to achieve. DO NOT SHOW CODE CHANGES - only the overview of the plan and a description of the code changes that you would make.

**Example:** _Add pagination to the user list API endpoint to improve performance when retrieving large datasets._

## Clarifying Questions

Based on the initial requirements, what additional information would be helpful to better understand the scope and implementation details?

**Example:**
* _What is the desired page size for the pagination?_
* _Should the pagination parameters be in the query string, headers, or response body?_
* _Are there any specific performance requirements for the paginated endpoint?_

If you have clarifying questions, ALWAYS ask these before proceeding.

## Code Analysis

### Current Implementation

Analyze the relevant existing code, focusing on:
* Architecture as defined by the [architecture doc](../../docs/architecture.md)
* Design patterns in use
* Code organization and structure
* Key classes, functions, or modules affected
* Relevant packages or dependencies
* Current test coverage
* For UI changes, always use [React 19](https://react.dev/blog/2024/12/05/react-19)

### Impact Analysis

Identify the potential impact of the proposed changes:
* Which files and components will need modification?
* Are there potential side effects or regression risks?
* Will database schemas, APIs, or interfaces need to change?
* Are there dependencies on other systems or services?
* What tests need to be added?

## Implementation Plan

Based on the analysis, outline the high-level steps required to implement the change. Include:

1. Preparation steps (e.g., creating feature branches)
2. Sequential implementation steps
3. Testing approaches
4. Integration considerations
5. Any necessary migration or deployment concerns

**Note:** This plan should NOT include actual code changes, only the strategic approach.

## Considerations and Trade-offs

Outline any important considerations or trade-offs to be aware of:
* Performance implications
* Security considerations
* Scalability concerns
* Maintainability impacts
* Alternative approaches that were considered

## Next Steps

Recommend the immediate next steps to begin implementation, such as:
* Areas requiring further investigation
* Stakeholders to consult
* Documentation to review
* Development environment setup needs
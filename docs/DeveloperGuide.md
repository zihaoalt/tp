# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

---

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Limit component

`Limit.java`: stores the limit as a static variable accessible across the application\
- using a static variable prevents inconsistent limit values 
- no need to pass a `Limit` object across class methods to check the limit

`SetLimitCommand.java`: handles validation and user confirmation logic\
- improves separation of concerns

`EditLimitCommand.java`: 

#### Setting the limit 

The sequence diagram below illustrates the interaction within the `Limit` component, with an input of `limit 100` 
from the user. 

![Set Limit Sequence Diagram](/docs/UML_diagrams/images/SetLimit.png)

How the `Limit` component sets a limit
1. The `Ui` will handle receiving the input and passes that input into `Finbro`.
2. `Finbro` passes the input into `Parser` to parse the input, which creates a `SetLimitCommand` object. 
3. Executing the command object will verify the user's input limit and if valid, will get confirmation from the user
to change the limit.
4. If user inputs "yes", then the limit will be changes accordingly, otherwise it remains unchanged.
5. `Ui` will show the updated limit. 
6. `Finbro` will update the limit in `Storage` file. 

---

## Product scope
### Target user profile

{Describe the target user profile}

This application is optimized for users who prefer fast keyboard input over graphical interfaces.

### Value proposition

{Describe the value proposition: what problem does it solve?}

This application helps users keep track of their spendings and gives frequent reminders to prevent them from spending
unnecessarily.

## User Stories

| Version | As a ...     | I want to ...                                             | So that I can ...                                           |
|---------|--------------|-----------------------------------------------------------|-------------------------------------------------------------|
| v1.0    | new user     | see usage instructions                                    | refer to them when I forget how to use the application      |
| v1.0    | new user     | record an expense by providing only amount and category   | start tracking my spending without learning many details    |
| v1.0    | new user     | see clear error messages when I enter invalid inputs      | correct mistakes without frustration                        |
| v1.0    | new user     | view a short help guide explaining available commands     | understand how to use the application                       | 
| v1.0    | regular user | record expenses with a description and a date             | have an accurate and meaningful spending history            |
| v1.0    | regular user | be able to delete my expenses                             | remove unnecessary expenses                                 |
| v1.0    | regular user | view total spending by category                           | understand where my money is going                          |
| v1.0    | regular user | set spending limits for a week/month and receive warnings | avoid overspending                                          |
| v2.0    | user         | find a to-do item by name                                 | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

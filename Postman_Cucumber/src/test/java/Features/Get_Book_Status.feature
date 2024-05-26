#    PLEASE CHANGE THE EMAIL IN THE EXAMPLES WHILE RUNNING THE FEATURE FILE EVERYTIME
Feature: Validating Book Status using Api.

  @StatusOfBook
  Scenario: Verify the Status of book is fetched using Get_Book_Status Api
    When user calls "Get_Book_Status" with Get http request
    Then the API call got Success with status code 200
    And user validates the "status" in the response body is "OK"

  @ListOfBooks
  Scenario: Verify that List of Books are fetched using Get_List_Book Api
    When user calls "Get_List_Book" with Get List of Books https Request
    Then the API call got Success with status code 200
    And then user validates the response Body

  @SingleBook
  Scenario Outline: Verify that Single Book is fetched using Get_Single_Book Api
    When user calls "Get_Single_Book" with "<BookID>" http request
    Then user extracts the Book Data from the particular BookId
    Examples:
      | BookID |
      | 1      |

  @Authentication
#    PLEASE CHANGE THE EMAIL IN THE EXAMPLES WHILE RUNNING THE FEATURE FILE EVERYTIME
  Scenario Outline: Verify that the Authentication code is generated
    When  user calls "Authentication" with "<name>" "<email>" with post http request
    And user extracts the Authentication code

    Examples:
      | name | email               |
      | Jim  | david17@example.com |

  @SubmitOrder
  Scenario Outline: Verify that the order is Submitted using AccessToken
#    When user calls "Submit_Order" with post Http request and AccessToken
    When user calls "Submit_Order" with "<id>" "<name>" post Http request and AccessToken
    Then user extracts the orderId
    Examples:
      | id | name |
      | 1  | Jim  |

  @GetAllOrder
  Scenario: Verify the order submitted with Get all orders api using AccessToken
    Then user calls "Get all orders" with Get http request and Access Token

  @GetAnOrder
  Scenario: Verify that a particular order is fetched using get http request and Access token
    Then user collects an particular order using orderId with get http request and AccessToken

  @UpdateOrderName
  Scenario Outline: verify that the order detail response has a name updated
    Then user updates the name "<upName>" using patch http request and accessToken and orderId
    Then user collects an particular order using orderId with get http request and AccessToken
    Examples:
      | upName     |
      | Glenn Maxi |

  @DeleteAnOrder
  Scenario: Verify that order is deleted
    Then user deletes the order using order id and access token
    Then user collects an particular order using orderId with get http request and AccessToken









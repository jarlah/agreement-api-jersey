# agreement api

# How to call the agreement api

```
~  curl -X POST -d '{ "customerPid": "04032367424", "customerName": "Jarl", "agreementPrice": 123.233, "agreementDate": "1999-01-24" }' -H "Content-Type: application/json" -H "X-Correlation-Id: a3dfbdf3-3fae-40e2-aa6e-de76c1220f65" http://localhost:8080/api/agreement
{"id":"1414eef5-dcfd-4b4d-9c98-f07e1ef1bab4","agreementPrice":123.233,"customerId":"6a1dfe10-fbf7-4776-aab6-95fb9b3708c4"}%
~ 
```

# todo

- [ ] Find out how to add correlationId filter for logging (not as easy as in spring boot)

# Possible agreement fields

inspiration taken from https://storman.com/api/rest/#create-agreement

```
customer_id	integer	The primary key of the Customer this agreement belongs to.	Yes
agreement_date	date	The date this agreement was created /signed.	Yes
external_contract_id	float	The external contract ID that links to Storman.	No
purchase_order_number	string	The purchase order number stored.	No
tax_exempt	boolean	Whether this agreement is exempted from tax or not.	No
billing_plan_id	string	The unique id identifying the billing plan for this agreement.	Yes
autopay_type	string	The autopay type. Possible values: BANK, CREDIT_CARD, AUTOPAY.	No
autopay_enabled	boolean	Whether autopay is enabled for this agreement or not.	No
autopay_authorised_amount	float	The amount authorised by the customer to be auto charged for this agreement.	No
autopay_authorised_from_date	date	From which date Storman is authorised to auto charge this agreement.	No
autopay_authorised_to_date	date	Until which date Storman is authorised to auto charge this agreement.	No
disable_auto_increase	boolean	Except the agreement from auto increase rules.	No
last_rent_increase_date	date	The date this agreement's rent was last increased.	No
last_late_cycle_start_date	date	The date the late cycle will commence.	No
corr_receive_sms	boolean	Whether to receive sms or not under this agreement.	No
corr_send_invoices	boolean	Whether to auto send regular invoices under this agreement.	No
corr_send_statements	boolean	Whether to auto send regular statements under this agreement.	No
receive_invoice_x_days_before	integer	The amount of days to send invoices prior to being due.	No
receive_email_or_letter	string	Whether to receive email or letter under this agreement. Possible values: EMAIL, LETTER.	Yes
insurance_policy_number	string	The agreement level insurance policy number stored.	No
insurance_coverage_amount	float	The agreement insurance coverage.	No
insurance_premium_amount	float	The agreement insurance premium amount.	No
intended_move_out_date	date	The date this agreement is expected to conclude.	No
notice_given_date	date	The date the customer has given notice to terminate this agreement.
```
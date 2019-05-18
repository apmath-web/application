# applications

## gradle commands
- update dependencies `./gradlew build --refresh-dependencies`
- run all tests `./gradlew test`
- run application `./gradlew run`

## examples
# Check Loan data correspond approved application info (all even clients approved, odd declined)
```
curl -i -X POST http://localhost:8080/v1/2/3 -H "Content-Type: application/json" -d '{"amount":1000000,"currency":RUR","term":6}'
```

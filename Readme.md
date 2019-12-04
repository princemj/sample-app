### Setup

#### Postgres installation on mac

- brew install postgres - skip if already installed
- login `psql postgres` 
- `\du` to list users
- `\l` to list databases.
- `CREATE ROLE test WITH LOGIN PASSWORD '<password_here>';` (password should be with in quote)
- `ALTER ROLE test CREATEDB;`
- `CREATE DATABASE sample;`
- login with `psql -Utest -dsample`


#### To Build
./gradlew clean build

#### To Run
./run.sh

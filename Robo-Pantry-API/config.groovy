environments {
    local {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog_master.xml'
                url = 'jdbc:postgresql://localhost:5433/robo-pantry-db'
                username = 'user'
                password = 'password!'
            }
        }
    }

    test {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog_master.xml'
                url = 'jdbc:postgresql://localhost:5433/robo-pantry-db'
                username = 'user'
                password = 'password!'
            }
        }
    }
}
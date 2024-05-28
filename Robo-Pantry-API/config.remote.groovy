environments {
    dev {
        spring {
            datasource {
                changelog = 'src/main/resources/db/changelog_master.xml'
                url = System.getenv('DEV_DATABASE_URL')
                username = System.getenv('DEV_DATABASE_USERNAME')
                password = System.getenv('DEV_DATABASE_PASSWORD')
            }
        }
    }
}
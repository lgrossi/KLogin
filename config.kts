gameServerConfig {
    name("Canary")
    host("127.0.0.1")
    port(7172)
    location("NL")
}

loginServerConfig {
    host("127.0.0.1")
    httpPort(80)
    rcpPort(9090)
    rateLimit(limit = 10, durationInSeconds = 1)
}

databaseConfig {
    host("127.0.0.1")
    port(3306)
    databaseName("canary")
    user("canary")
    password("canary")
}

vocations {
    vocation("Sorcerer")
    vocation("Druid")
    vocation("Paladin")
    vocation("Knight")
    vocation("Master Sorcerer")
    vocation("Elder Druid")
    vocation("Royal Paladin")
    vocation("Elite Knight")
}
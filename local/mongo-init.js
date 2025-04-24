db.createUser(
    {
        user: "catalog",
        pwd: "Mudar123!",
        roles: [
            {
                role: "readWrite",
                db: "catalog"
            }
        ]
    }
);
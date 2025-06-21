| Endpoint                        | Method | Auth      | Description                    |
| ------------------------------- | ------ | --------- | ------------------------------ |
| `/auth/register`                | POST   | ❌         | Register user                  | done verified
| `/auth/login`                   | POST   | ❌         | Login and return JWT           | done verified
| `/complaints`                   | POST   | ✅         | Create complaint               | done verified
| `/complaints`                   | GET    | ✅         | List current user’s complaints | done verified
| `/complaints/{id}`              | GET    | ✅         | Get single complaint           | done verified
| `/complaints/{id}`              | PUT    | ✅         | Update if user is owner        | done verified
| `/complaints/{id}`              | DELETE | ✅         | Delete if user is owner        | done verified
| `/admin/complaints`             | GET    | ✅ (Admin) | Get all complaints             | done verified
| `/admin/complaints/{id}/status` | PUT    | ✅ (Admin) | Update complaint status        | done verified
| `/categories`                   | GET    | ✅         | List categories                | done verified

| Endpoint                        | Method | Auth      | Description                    |
| ------------------------------- | ------ | --------- | ------------------------------ |
| `/auth/register`                | POST   | ❌         | Register user                  | done
| `/auth/login`                   | POST   | ❌         | Login and return JWT           | done
| `/complaints`                   | POST   | ✅         | Create complaint               | done
| `/complaints`                   | GET    | ✅         | List current user’s complaints | done
| `/complaints/{id}`              | GET    | ✅         | Get single complaint           |
| `/complaints/{id}`              | PUT    | ✅         | Update if user is owner        |
| `/complaints/{id}`              | DELETE | ✅         | Delete if user is owner        |
| `/admin/complaints`             | GET    | ✅ (Admin) | Get all complaints             |
| `/admin/complaints/{id}/status` | PUT    | ✅ (Admin) | Update complaint status        |
| `/categories`                   | GET    | ✅         | List categories                | done

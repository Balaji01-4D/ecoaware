| Endpoint                        | Method | Auth      | Description                    |
| ------------------------------- | ------ | --------- | ------------------------------ |
| `/auth/register`                | POST   | ❌         | Register user                  | done VERIFIED
| `/auth/login`                   | POST   | ❌         | Login and return JWT           | done
| `/complaints`                   | POST   | ✅         | Create complaint               | done VERIFIED
| `/complaints`                   | GET    | ✅         | List current user’s complaints | done VERIFIED
| `/complaints/{id}`              | GET    | ✅         | Get single complaint           | done VERIFIED
| `/complaints/{id}`              | PUT    | ✅         | Update if user is owner        | done VERIFIED
| `/complaints/{id}`              | DELETE | ✅         | Delete if user is owner        | done VERIFIED
| `/admin/complaints`             | GET    | ✅ (Admin) | Get all complaints             | done VERIFIED
| `/admin/complaints/{id}/status` | PUT    | ✅ (Admin) | Update complaint status        | done  VERIFIED
| `/categories`                   | GET    | ✅         | List categories                | done VERIFIED

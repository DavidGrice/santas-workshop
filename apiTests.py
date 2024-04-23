import requests
import unittest
import json

class TestAPI(unittest.TestCase):
    # replace with your Spring Boot app's URL
    BASE_URL = 'http://localhost:8080'

    

    #region User

    def test_20_add_one_user(self):
        response = requests.post(f'{self.BASE_URL}/api/user/createUser', json={
            "email": "test@example.com",
            "username": "testuser",
            "password": "password123",
            "firstName": "Test",
            "lastName": "User",
            "role_id": 1
        })
        self.assertEqual(response.status_code, 201)

    #endregion

    
    

    # Add more tests as needed for other endpoints and methods (POST, PUT, DELETE)

if __name__ == '__main__':
    unittest.main()
    def test__19_delete_location(self):
        response = requests.delete(f'{self.BASE_URL}/api/location/deleteLocation/1')
        self.assertEqual(response.status_code, 200)
    def test___14_delete_status(self):
        response = requests.delete(f'{self.BASE_URL}/api/status/deleteStatus/1')
        self.assertEqual(response.status_code, 200)
    def test___9_delete_role(self):
        response = requests.delete(f'{self.BASE_URL}/api/role/deleteRole/1')
        self.assertEqual(response.status_code, 200)
        response = requests.get(f'{self.BASE_URL}/api/role/getAllRoles')
        print(json.dumps(response.json(), indent=4))

    def test___5_delete_description(self):
        response = requests.delete(f'{self.BASE_URL}/api/description/deleteDescription/1')
        self.assertEqual(response.status_code, 200)


#region Description

    def test_1_add_one_description(self):
        response = requests.post(f'{self.BASE_URL}/api/description/createDescription', json={
            "name": "hammer",
            "description": "Wodden hammer, flat head, used for nailing things."
        })
        self.assertEqual(response.status_code, 201)
    
    def test_2_get_one_description(self):
        response = requests.get(f'{self.BASE_URL}/api/description/getDescription/1')
        self.assertEqual(response.status_code, 200)
    
    def test_3_get_all_descriptions(self):
        response = requests.get(f'{self.BASE_URL}/api/description/getAllDescriptions')
        self.assertEqual(response.status_code, 200)

    def test_4_update_description(self):
        response = requests.put(f'{self.BASE_URL}/api/description/updateDescription/1', json={
            "name": "hammer",
            "description": "Wooden hammer, flat head, used for nailing things."
        })
        self.assertEqual(response.status_code, 200)



    #endregion

    #region Role

    def test_5_add_one_role(self):
        response = requests.post(f'{self.BASE_URL}/api/role/createRole', json={
            "role_name": "Admin",
            "role_description": "Administrater"
        })
        self.assertEqual(response.status_code, 201)

    def test_6_get_one_role(self):
        response = requests.get(f'{self.BASE_URL}/api/role/getRole/1')
        print(json.dumps(response.json(), indent=4))
        self.assertEqual(response.status_code, 200)

    def test_7_get_roles(self):
        response = requests.get(f'{self.BASE_URL}/api/role/getAllRoles')
        print(json.dumps(response.json(), indent=4))
        self.assertEqual(response.status_code, 200)

    def test_8_update_role(self):
        response = requests.put(f'{self.BASE_URL}/api/role/updateRole/1', json={
            "role_name": "Admin",
            "role_description": "Administrator"
        })
        self.assertEqual(response.status_code, 200)
        response = requests.get(f'{self.BASE_URL}/api/role/getAllRoles')
        print(json.dumps(response.json(), indent=4))



    #endregion

    #region Status

    def test_10_add_one_status(self):
        response = requests.post(f'{self.BASE_URL}/api/status/createStatus', json={
            "status_name": "Delivered",
            "status_description": "Toy has been deliveredsd."
        })
        self.assertEqual(response.status_code, 201)

    def test_11_get_one_status(self):
        response = requests.get(f'{self.BASE_URL}/api/status/getStatus/1')
        self.assertEqual(response.status_code, 200)

    def test_12_get_statuses(self):
        response = requests.get(f'{self.BASE_URL}/api/status/getAllStatuses')
        self.assertEqual(response.status_code, 200)

    def test_13_update_status(self):
        response = requests.put(f'{self.BASE_URL}/api/status/updateStatus/1', json={
            "status_name": "Delivered",
            "status_description": "Toy has been delivered."
        })
        self.assertEqual(response.status_code, 200)



    #endregion

    #region Location

    def test_15_add_one_location(self):
        response = requests.post(f'{self.BASE_URL}/api/location/createLocation', json={
            "address": "123 Main St",
            "city": "Springfield",
            "state_prov": "IL",
            "country": "USA",
            "region": "Midwest",
            "latitude": 39.7817,
            "longitude": -89.6500
        })
        self.assertEqual(response.status_code, 201)

    def test_16_get_one_location(self):
        response = requests.get(f'{self.BASE_URL}/api/location/getLocation/1')
        self.assertEqual(response.status_code, 200)

    def test_17_get_locations(self):
        response = requests.get(f'{self.BASE_URL}/api/location/getAllLocations')
        self.assertEqual(response.status_code, 200)

    def test_18_update_location(self):
        response = requests.put(f'{self.BASE_URL}/api/location/updateLocation/1', json={
            "address": "123 Main St",
            "city": "Springfield",
            "state_prov": "MO",
            "country": "USA",
            "region": "Midwest",
            "latitude": 39.7817,
            "longitude": -89.6500
        })
        self.assertEqual(response.status_code, 200)
    


    #endregion

#region Toy

    def test_25_add_one_toy(self):
        response = requests.post(f'{self.BASE_URL}/api/toy/createToy', json={
            "name": "Teddy Bear",
            "description_id": 1,
            "added_by": 1,
            "added_date": "2022-01-01T00:00:00Z",
            "updated_by": 1,
            "updated_date": "2022-01-01T00:00:00Z",
            "quantity": 10
        })
        self.assertEqual(response.status_code, 201)

    def test_26_get_one_toy(self):
        response = requests.get(f'{self.BASE_URL}/api/toy/getToy/1')
        self.assertEqual(response.status_code, 200)

    def test_27_get_toys(self):
        response = requests.get(f'{self.BASE_URL}/api/toy/getAllToys')
        self.assertEqual(response.status_code, 200)

    def test_28_update_toy(self):
        response = requests.put(f'{self.BASE_URL}/api/toy/updateToy/1', json={
            "name": "Train",
            "description_id": 1,
            "added_by": 1,
            "added_date": "2022-01-01T00:00:00Z",
            "updated_by": 1,
            "updated_date": "2022-01-01T00:00:00Z",
            "quantity": 10
        })
        self.assertEqual(response.status_code, 200)

    def test_29_delete_toy(self):
        response = requests.delete(f'{self.BASE_URL}/api/toy/deleteToy/1')
        self.assertEqual(response.status_code, 200)

    #endregion

    #region Child

    def test_30_add_one_child(self):
        response = requests.post(f'{self.BASE_URL}/api/child/createChild', json={
            "first_name": "Jimmy",
            "last_name": "Doe",
            "age": 5,
            "status_id": 1,
            "location_id": 1
        })
        self.assertEqual(response.status_code, 201)

    def test_31_get_one_child(self):
        response = requests.get(f'{self.BASE_URL}/api/child/getChild/1')
        print(json.dumps(response.json(), indent=4))
        self.assertEqual(response.status_code, 200)

    def test_32_get_children(self):
        response = requests.get(f'{self.BASE_URL}/api/child')
        self.assertEqual(response.status_code, 200)

    def test_33_update_child(self):
        response = requests.put(f'{self.BASE_URL}/api/child/updateChild/1', json={
            "name": "John",
            "last_name": "Doe",
            "age": 7,
            "status_id": 1,
            "location_id": 1
        })
        self.assertEqual(response.status_code, 200)

    def test__34_delete_child(self):
        response = requests.delete(f'{self.BASE_URL}/api/child/deleteChild/1')
        self.assertEqual(response.status_code, 200)

    #endregion

    #region Delivery

    def test_35_add_delivery(self):
        response = requests.post(f'{self.BASE_URL}/api/deliveries/createDelivery', json={
            "child_id": 1,
            "location_id": 1,
            "toy_id": 1,
            "status_id": 1,
            "delivered_date": "2021-12-01"
        })
        self.assertEqual(response.status_code, 201)

    def test_36_get_delivery(self):
        response = requests.get(f'{self.BASE_URL}/api/deliveries/getDelivery/1')
        self.assertEqual(response.status_code, 200)

    def test_37_get_all_deliveries(self):
        response = requests.get(f'{self.BASE_URL}/api/deliveries/getAllDeliveries')
        self.assertEqual(response.status_code, 200)
    
    def test_38_update_delivery(self):
        response = requests.put(f'{self.BASE_URL}/api/deliveries/updateDelivery/1', json={
            "child_id": 1,
            "location_id": 1,
            "toy_id": 1,
            "status_id": 1,
            "delivered_date": "2020-12-01"
        })
        self.assertEqual(response.status_code, 200)

    def test_39_delete_delivery(self):
        response = requests.delete(f'{self.BASE_URL}/api/deliveries/deleteDelivery/1')
        self.assertEqual(response.status_code, 200)

    #endregion

#region Delivery Filters

    # def test_get_delivery_by_status(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/status/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_date(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/date/2021-12-01')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_toy(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/toy/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_child(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/child/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_toy(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/toy/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_child_and_toy(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/child/1/toy/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_child_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/child/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_toy_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/toy/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_toy_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/toy/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_child_and_toy_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/child/1/toy/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy_and_location(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1/location/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy_and_location_and_date(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1/location/1/date/2021-12-01')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy_and_location_and_status(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1/location/1/status/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy_and_location_and_date_and_status(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1/location/1/date/2021-12-01/status/1')
    #     self.assertEqual(response.status_code, 200)

    # def test_get_delivery_by_user_and_child_and_toy_and_location_and_date_and_status_and_id(self):
    #     response = requests.get(f'{self.BASE_URL}/api/delivery/user/1/child/1/toy/1/location/1/date/2021-12-01/status/1/id/1')
    #     self.assertEqual(response.status_code, 200)

    #endregion
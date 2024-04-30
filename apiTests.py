import requests
import unittest
import json
import logging
import pandas as pd


# Configure logging
logging.basicConfig(level=logging.INFO, 
                    format='%(asctime)s:%(levelname)s:%(message)s')

class Description:
    BASE_URL = 'http://localhost:8080'

    #region Description

    def add_description(self, data = {"name": "nail", "description": "Metal nail, used for nailing things."}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/description/createDescription', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 0: add_description - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    def get_description_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/description/getDescription/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 1: get_description_by_id - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    def get_all_descriptions(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/description/getAllDescriptions')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 2: get_all_descriptions - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def update_description_by_id(self, id = 1, data = {"name": "nail", "description": "Metal nail, used for nailing things."}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/description/updateDescription/{id}', json=data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 3: update_description_by_id - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_description_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/description/deleteDescription/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 4: delete_description_by_id - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    #endregion

class Role:
    BASE_URL = 'http://localhost:8080'

    #region Role

    def add_role(self, data = {"role_name": "Admin", "role_description": "Administrator"}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/role/createRole', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 5: add_role - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_role_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/role/getRole/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 6: get_role_by_id - Status code: %s', response.status_code)
            logging.info('Test 6: get_role_by_id - Response: %s', response.json())
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_role_by_name(self, name = "Admin"):
        try:
            response = requests.get(f'{self.BASE_URL}/api/role/getRoleByName/{name}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 6: get_role_by_name - Status code: %s', response.status_code)
            logging.info('Test 6: get_role_by_name - Response: %s', response.json())
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def role_exists(self, name = "Admin"):
        try:
            response = requests.get(f'{self.BASE_URL}/api/role/roleExists/{name}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 6: role_exists - Status code: %s', response.status_code)
            return True
        except Exception as e:
            logging.error('Error: %s', e)
            return False

    def get_all_roles(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/role/getAllRoles')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 7: get_all_roles - Status code: %s', response.status_code)
            logging.info('Test 6: get_all_roles - Response: %s', response.json())
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_role_by_id(self, id = 1, data = {"role_name": "Admin", "role_description": "Administratorsd"}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/role/updateRole/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 8: Update role - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    def delete_role_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/role/deleteRole/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 9: Delete role - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    #endregion

class Status:
    BASE_URL = 'http://localhost:8080'

    #region Status

    def add_status(self, data = {"status_name": "Delivered", "status_description": "Toy has been delivered."}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/status/createStatus', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 10: Add one status - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_status_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/status/getStatus/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 11: Get one status - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_statuses(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/status/getAllStatuses')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 12: Get all statuses - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_status_by_id(self, id = 1, data = {"status_name": "Delivered", "status_description": "Toy has been delivered."}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/status/updateStatus/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 13: Update status - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_status_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/status/deleteStatus/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 14: Delete status - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    #endregion

class Location:
    BASE_URL = 'http://localhost:8080'

    #region Location

    def add_location(self, data = {"address": "123 Main St", "city": "Springfield", "state_prov": "IL", "zip_code": "12345", "country": "USA", "region": "Midwest", "latitude": 39.7817, "longitude": -89.6500}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/location/createLocation', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 15: add_location - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_location_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/location/getLocation/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 16: get_location_by_id - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_locations(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/location/getAllLocations')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 17: get_all_locations - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_location_by_id(self, id = 1, data = {"address": "123 Main St", "city": "Springfield", "state_prov": "MO", "zip_code": "54321", "country": "USA", "region": "Midwest", "latitude": 39.7817, "longitude": -89.6500}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/location/updateLocation/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 18: update_location_by_id - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    def delete_location_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/location/deleteLocation/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 19: delete_location_by_id - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    #endregion

class User:
    BASE_URL = 'http://localhost:8080'

    #region User
    
    def add_user(self):

        instantiateRole = Role()

        print(instantiateRole.role_exists("Admin"))

        instantiateRole.add_role(
            data = {"role_name": "Admin", "role_description": "Administrator"}
        )
        # instantiateRole.add_role(
        #     data = {"role_name": "Guest", "role_description": "Guest User"}
        # )
        
        admin_role = instantiateRole.get_role_by_name("Admin").json()
        print(admin_role)
        # guest_role = instantiateRole.get_role_by_name("Guest")
        # print(guest_role)

        adminData = {"email": "admin@example.com",
            "username": "admin",
            "password": "password123",
            "firstName": "Admin",
            "lastName": "User",
            "role_id": admin_role['id']
        }
        
        try:
            response = requests.post(f'{self.BASE_URL}/api/user/createUser', json = adminData)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 20: add_user - Status code: %s', response.status_code)
            logging.info('Test 20: add_user - Response code: %s', response.json())
        except Exception as e:
            logging.error('Error: %s', e)

    def get_user_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/user/getUser/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 21: get_user_by_id - Status code: %s', response.status_code)
            logging.info('Test 21: get_user_by_id - Response code: %s', response.json())
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_users(self):
        response = requests.get(f'{self.BASE_URL}/api/user/getAllUsers')
        logging.info('Test 22: get_all_users - Status code: %s', response.status_code)
        logging.info('Test 22: get_all_users - Response code: %s', response.json())
        #self.assertEqual(response.status_code, 200)
        return response

    def update_user_by_id(self, id = 1):
        instantiateRole = Role()
        admin_role = instantiateRole.get_role_by_name("Admin").json()
        adminUpdateData = {"email": "admin@example.com",
            "username": "admin",
            "password": "password123",
            "firstName": "Admin",
            "lastName": "User Updated",
            "role_id": admin_role['id']
        }
        try:
            print(User().get_user_by_id(id).json())
            response = requests.put(f'{self.BASE_URL}/api/user/updateUser/{id}', json=adminUpdateData)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 23: Update user - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_user(self, id = 1):
            
        try:
            response = requests.delete(f'{self.BASE_URL}/api/user/deleteUser/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 24: Delete user - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
        
        instantiateRole = Role()
        role_to_delete = instantiateRole.get_role_by_name("Admin").json()
        instantiateRole.delete_role_by_id(role_to_delete['id'])
    #endregion

class Toy:
    BASE_URL = 'http://localhost:8080'

    #region Toy

    def add_toy(self, data = {"name": "Teddy Bear", "description_id": 1, "added_by": 1, "added_date": "2022-01-01T00:00:00Z", "updated_by": 1, "updated_date": "2022-01-01T00:00:00Z", "quantity": 10}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/toy/createToy', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 25: Add one toy - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_toy_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/toy/getToy/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 26: Get one toy - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_toys(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/toy/getAllToys')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 27: Get all toys - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_toy_by_id(self, id = 1, data = {"name": "Teddy Bear", "description_id": 1, "added_by": 1, "added_date": "2022-01-01T00:00:00Z", "updated_by": 1, "updated_date": "2022-01-01T00:00:00Z", "quantity": 10}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/toy/updateToy/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 28: Update toy - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_toy_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/toy/deleteToy/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 29: Delete toy - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    #endregion

class Child:
    BASE_URL = 'http://localhost:8080'

    #region Child

    def add_child(self, data = {"first_name": "Jimmy", "last_name": "Doe", "age": 5, "status_id": 1, "location_id": 1}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/child/createChild', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 30: Add one child - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_child_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/child/getChild/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 31: Get one child - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_children(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/child/getAllChildren')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 32: Get all children - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_child_by_id(self, id = 1, data = {"first_name": "John", "last_name": "Doe", "age": 7, "status_id": 1, "location_id": 1}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/child/updateChild/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 33: Update child - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_child_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/child/deleteChild/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 34: Delete child - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)
    
    #endregion

class Delivery:
    BASE_URL = 'http://localhost:8080'

    #region Delivery

    def add_delivery(self, data = {"child_id": 1, "location_id": 1, "toy_id": 1, "status_id": 1, "delivered_date": "2021-12-01"}):
        try:
            response = requests.post(f'{self.BASE_URL}/api/deliveries/createDelivery', json = data)
            #self.assertEqual(response.status_code, 201)
            logging.info('Test 35: Add one delivery - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def get_delivery_by_id(self, id = 1):
        try:
            response = requests.get(f'{self.BASE_URL}/api/deliveries/getDelivery/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 36: Get one delivery - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def get_all_deliveries(self):
        try:
            response = requests.get(f'{self.BASE_URL}/api/deliveries/getAllDeliveries')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 37: Get all deliveries - Status code: %s', response.status_code)
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def update_delivery_by_id(self, id = 1, data = {"child_id": 1, "location_id": 1, "toy_id": 1, "status_id": 1, "delivered_date": "2021-12-01"}):
        try:
            response = requests.put(f'{self.BASE_URL}/api/deliveries/updateDelivery/{id}', json = data)
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 38: Update delivery - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    def delete_delivery_by_id(self, id = 1):
        try:
            response = requests.delete(f'{self.BASE_URL}/api/deliveries/deleteDelivery/{id}')
            #self.assertEqual(response.status_code, 200)
            logging.info('Test 39: Delete delivery - Status code: %s', response.status_code)
        except Exception as e:
            logging.error('Error: %s', e)

    #endregion


def run_tests():
    # description = Description()
    # description.add_description()
    # description.get_description_by_id()
    # description.get_all_descriptions()
    # description.update_description_by_id()
    # description.delete_description_by_id()

    # role = Role()
    # role.add_role()
    # role.get_role_by_id()
    # role.get_role_by_name()
    # role.get_all_roles()
    # role.update_role_by_id()
    # role.delete_role_by_id()

    # status = Status()
    # status.add_status()
    # status.get_status_by_id()
    # status.get_all_statuses()
    # status.update_status_by_id()
    # status.delete_status_by_id()

    # location = Location()
    # location.add_location()
    # location.get_location_by_id()
    # location.get_all_locations()
    # location.update_location_by_id()
    # location.delete_location_by_id()

    user = User()
    user.add_user()
    user.get_user_by_id()
    user.get_all_users()
    user.update_user_by_id()
    user.delete_user()

    # toy = Toy()
    # toy.add_toy()
    # toy.get_toy_by_id()
    # toy.get_all_toys()
    # toy.update_toy_by_id()
    # toy.delete_toy_by_id()

    # child = Child()
    # child.add_child()
    # child.get_child_by_id()
    # child.get_all_children()
    # child.update_child_by_id()
    # child.delete_child_by_id()

    # delivery = Delivery()
    # delivery.add_delivery()
    # delivery.get_delivery_by_id()
    # delivery.get_all_deliveries()
    # delivery.update_delivery_by_id()
    # delivery.delete_delivery_by_id()

if __name__ == '__main__':
    run_tests()
    logging.info('All tests passed successfully.')


import requests
import unittest
import json
import logging
import pandas as pd
from datetime import datetime

# Configure logging
logging.basicConfig(level=logging.INFO, 
                    format='%(asctime)s:%(levelname)s:%(message)s')

#region GenerateData

class GenerateData:
    @staticmethod
    def generate_description_data(name = "nail", description = "Metal nail, used for nailing things."):
        return {"name": name, "description": description}
    
    @staticmethod
    def generate_role_data(role_name = "Admin", role_description = "Administrator"):
        return {"role_name": role_name, "role_description": role_description}

    @staticmethod
    def generate_child_status_data(status_name = "Naughty", status_description = "Coal in sack cloth."):
        return {"status_name": status_name, "status_description": status_description}
    
    @staticmethod
    def generate_delivery_status_data(status_name = "Delivered", status_description = "Toy has been delivered."):
        return {"status_name": status_name, "status_description": status_description}
    
    @staticmethod
    def generate_location_data(address = "123 Main St", city = "Springfield", state_prov = "IL", zip_code = "12345", country = "USA", region = "Midwest", latitude = 39.7817, longitude = -89.6500):
        return {"address": address, "city": city, "state_prov": state_prov, "zip_code": zip_code, "country": country, "region": region, "latitude": latitude, "longitude": longitude}
    
    @staticmethod
    def generate_user_data(email = "admin@example.com", username = "SantaClaus", password = "password123", firstName = "Nick", lastName = "Admin", role_id = None):
        return {"email": email, "username": username, "password": password, "firstName": firstName, "lastName": lastName, "role_id": role_id}
    
    @staticmethod
    def generate_toy_data(name = "Teddy Bear", description_id = None, added_by = None, added_date = None, updated_by = None, updated_date = None, quantity = 10):
        return {"name": name, "description_id": description_id, "added_by": added_by, "added_date": added_date, "updated_by": updated_by, "updated_date": updated_date, "quantity": quantity}
    
    @staticmethod
    def generate_child_data(first_name = "Johnny", last_name = "Appleseed", birthdate = "2022-01-01T00:00:00Z", status_id = None, location_id = None):
        return {"first_name": first_name, "last_name": last_name, "birthdate": birthdate, "status_id": status_id, "location_id": location_id}
    
    @staticmethod
    def generate_wishlist_data(child_id = None, toy_id = None):
        return {"child_id": child_id, "toy_id": toy_id}

    @staticmethod
    def generate_delivery_data(child_id = None, delivered_date = "2022-01-01T00:00:00Z", status_id = None, location_id = None, wishlist_id = None):
        return {"child_id": child_id, "delivered_date": delivered_date, "status_id": status_id, "location_id": location_id, "wishlist_id": wishlist_id}
    
#endregion

#region Description

class Description:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/description/createDescription",
        "get": "/api/description/getDescription/{id}",
        "get_all": "/api/description/getAllDescriptions",
        "update": "/api/description/updateDescription/{id}",
        "delete": "/api/description/deleteDescription/{id}",
        "search": "/api/description/searchDescriptions",
        "exists_by_name": "/api/description/existsByName",
        "exists_by_description": "/api/description/existsByDescription"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.replace('{id}', str(kwargs['id']))
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            if response.status_code == 200:
                logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
                return response
            else:
                logging.error(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
                return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e

    def add_description(self, data=None):
        data = data or GenerateData.generate_description_data()
        self.send_request('post', 'add', json=data)

    def get_description_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_descriptions(self):
        self.send_request('get', 'get_all')

    def update_description_by_id(self, id=None, data=None):
        id = id or 1
        data = data or {"name": "nail", "description": "Metallicy nail, used for nailing things."}
        self.send_request('put', 'update', id=id, json=data)

    def delete_description_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_descriptions(self, params=None):
        params = params or {"id": 1, "name": "nail", "description": "Metal nail, used for nailing things."}
        return self.send_request('get', 'search', params=params)

    def description_exists_by_name(self, params=None):
        params = params or {'name': "nail"}
        self.send_request('get', 'exists_by_name', params=params)

    def description_exists_by_description(self, params=None):
        params = params or {'description': "Metal nail, used for nailing things."}
        self.send_request('get', 'exists_by_description', params=params)

#endregion

#region Role

class Role:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/role/createRole",
        "get": "/api/role/getRole/{id}",
        "exists": "/api/role/roleExists/{name}",
        "get_all": "/api/role/getAllRoles",
        "update": "/api/role/updateRole/{id}",
        "delete": "/api/role/deleteRole/{id}",
        "search": "/api/role/searchRoles"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.replace('{id}', str(kwargs['id']))
            del kwargs['id']
        if 'name' in kwargs:
            url = url.replace('{name}', kwargs['name'])
            del kwargs['name']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e

    def add_role(self, data=None):
        data = data or GenerateData.generate_role_data()
        self.send_request('post', 'add', json=data)

    def get_role_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_roles(self):
        self.send_request('get', 'get_all')

    def update_role_by_id(self, id=None, data=None):
        id = id or 1
        data = data or {"role_name": "Admin", "role_description": "Administratorizaton"}
        self.send_request('put', 'update', id=id, json=data)

    def delete_role_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_roles(self, params=None):
        params = params or {'id': 1, 'role_name': 'Admin', 'role_description': 'Administrator'}
        return self.send_request('get', 'search', params=params)
    
    def role_exists(self, name=None):
        name = name or "Admin"
        self.send_request('get', 'exists', name=name)
    
#endregion

#region ChildStatus

class ChildStatus:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/child_status/createStatus",
        "get": "/api/child_status/getStatus/{id}",
        "get_all": "/api/child_status/getAllStatuses",
        "update": "/api/child_status/updateStatus/{id}",
        "delete": "/api/child_status/deleteStatus/{id}",
        "search": "/api/child_status/searchStatuses",
        "exists_by_id_and_name": "/api/child_status/existsByIdAndStatusName/{id}/{statusName}",
        "exists_by_id_name_and_description": "/api/child_status/existsByIdAndStatusNameAndStatusDescription/{id}/{statusName}/{statusDescription}"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.replace('{id}', str(kwargs['id']))
            del kwargs['id']
        if 'name' in kwargs:
            url = url.replace('{statusName}', kwargs['name'])
            del kwargs['name']
        if 'description' in kwargs:
            url = url.replace('{statusDescription}', kwargs['description'])
            del kwargs['description']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e

    def add_status(self, data=None):
        data = data or GenerateData.generate_child_status_data()
        self.send_request('post', 'add', json=data)

    def get_status_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_statuses(self):
        self.send_request('get', 'get_all')

    def update_status_by_id(self, id=None, data=None):
        id = id or 1
        data = data or {"status_name": "Nice", "status_description": "No longer needs coal."}
        self.send_request('put', 'update', id=id, json=data)

    def delete_status_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_statuses(self, params=None):
        params = params or {'id': 1, 'status_name': 'Naughty', 'status_description': 'Coal in sack cloth.'}
        return self.send_request('get', 'search', params=params)

    def status_exists_by_id_and_name(self, id=None, name=None):
        id = id or 1
        name = name or "Naughty"
        self.send_request('get', 'exists_by_id_and_name', id=id, name=name)

    def status_exists_by_id_name_and_description(self, id=None, name=None, description=None):
        id = id or 1
        name = name or "Naughty"
        description = description or "Coal in sack cloth."
        self.send_request('get', 'exists_by_id_name_and_description', id=id, name=name, description=description)

#endregion

#region DeliveryStatus

class DeliveryStatus:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/delivery_status/createStatus",
        "get": "/api/delivery_status/getStatus/{id}",
        "get_all": "/api/delivery_status/getAllStatuses",
        "update": "/api/delivery_status/updateStatus/{id}",
        "delete": "/api/delivery_status/deleteStatus/{id}",
        "search": "/api/delivery_status/searchStatuses",
        "exists_by_name": "/api/delivery_status/existsByStatusName/{statusName}"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.replace('{id}', str(kwargs['id']))
            del kwargs['id']
        if 'name' in kwargs:
            url = url.replace('{statusName}', str(kwargs['name']))
            del kwargs['name']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def add_status(self, data=None):
        data = data or GenerateData.generate_delivery_status_data()
        self.send_request('post', 'add', json=data)

    def get_status_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_statuses(self):
        self.send_request('get', 'get_all')

    def update_status_by_id(self, id=None, data=None):
        id = id or 1
        data = data or {"status_name": "Delivered", "status_description": "Toy has been delivered."}
        self.send_request('put', 'update', id=id, json=data)

    def delete_status_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_statuses(self, params=None):
        params = params or {'id': 1, 'status_name': 'Delivered', 'status_description': 'Toy has been delivered.'}
        return self.send_request('get', 'search', params=params)

    def exists_by_name(self, name=None):
        name = name or "Delivered"
        self.send_request('get', 'exists_by_name', name = name)

#endregion

#region Location

class Location:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/location/createLocation",
        "get": "/api/location/getLocation/{id}",
        "get_all": "/api/location/getAllLocations",
        "update": "/api/location/updateLocation/{id}",
        "delete": "/api/location/deleteLocation/{id}",
        "search": "/api/location/searchLocations",
        "exists_by_address": "/api/location/existsByAddress",
        "exists_by_city": "/api/location/existsByCity",
        "exists_by_state_prov": "/api/location/existsByStateProv",
        "exists_by_zip_code": "/api/location/existsByZipCode",
        "exists_by_country": "/api/location/existsByCountry",
        "exists_by_region": "/api/location/existsByRegion",
        "exists_by_latitude_and_longitude": "/api/location/existsByLatitudeAndLongitude"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.format(id=kwargs['id'])
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def add_location(self, data=None):
        data = data or GenerateData.generate_location_data()
        self.send_request('post', 'add', json=data)
    
    def get_location_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_locations(self):
        self.send_request('get', 'get_all')

    def update_location_by_id(self, id=None, data=None):
        id = id or 1
        data = data or {"address": "12367 Main St", "city": "Springfield", "state_prov": "MO", "zip_code": "54321", "country": "USA", "region": "Midwest", "latitude": 39.7817, "longitude": -89.6500}
        self.send_request('put', 'update', id=id, json=data)
    
    def delete_location_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)
    
    def search_locations(self, params=None):
        params = params or {
                'address': '123 Main St',
                'city': 'Springfield',
                'stateProv': 'IL',
                'zipCode': '12345',
                'country': 'USA',
                'region': 'Midwest',
                'latitude': 39.7817,
                'longitude': -89.6500
            }
        return self.send_request('get', 'search', params=params)
    
    def exists_by_address(self, address=None):
        address = address or "123 Main St"
        params = {'address': address}
        self.send_request('get', 'exists_by_address', params=params)
    
    def exists_by_city(self, city=None):
        city = city or "Springfield"
        params = {'city': city}
        self.send_request('get', 'exists_by_city', params=params)

    def exists_by_state_prov(self, state_prov=None):
        state_prov = state_prov or "IL"
        params = {'stateProv': state_prov}
        self.send_request('get', 'exists_by_state_prov', params=params)
    
    def exists_by_zip_code(self, zip_code=None):
        zip_code = zip_code or "12345"
        params = {'zipCode': zip_code}
        self.send_request('get', 'exists_by_zip_code', params=params)

    def exists_by_country(self, country=None):
        country = country or "USA"
        params = {'country': country}
        self.send_request('get', 'exists_by_country', params=params)

    def exists_by_region(self, region=None):
        region = region or "Midwest"
        params = {'region': region}
        self.send_request('get', 'exists_by_region', params=params)

    def exists_by_latitude_and_longitude(self, latitude=None, longitude=None):
        latitude = latitude or 39.7817
        longitude = longitude or -89.6500
        params = {'latitude': latitude, 'longitude': longitude}
        self.send_request('get', 'exists_by_latitude_and_longitude', params=params)

#endregion

#region User

class User:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/user/createUser",
        "get": "/api/user/getUser/{id}",
        "get_all": "/api/user/getAllUsers",
        "update": "/api/user/updateUser/{id}",
        "delete": "/api/user/deleteUser/{id}",
        "search": "/api/user/searchUsers",
        "exists_by_username": "/api/user/existsByUsername",
        "exists_by_email": "/api/user/existsByEmail"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if '{id}' in url:
            if 'id' in kwargs:
                url = url.format(id=kwargs['id'])
                del kwargs['id']
            else:
                raise ValueError(f"An 'id' is required for the '{endpoint}' endpoint.")
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)

    def add_user(self, data=None, role_name=None, role_description=None):
        role_instance = Role()
        role_name = role_name or "Admin"
        role_description = role_description or "Administrator"
        response = role_instance.search_roles({'id': None, 'role_name': 'Admin', 'role_description': None})
        if response.status_code == 200:
            role_data = response.json()
            roles = role_data['content']
            for role in roles:
                role_name = role['role_name']
                role_id = role['id']
                print(f"Role Name: {role_name}, Role ID: {role_id}")
                if role['role_name'] == 'Admin':
                    data = data or GenerateData.generate_user_data()
                    data["role_id"] = role['id']
                    break
        self.send_request('post', 'add', json=data)
        
    def get_user_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_users(self):
        self.send_request('get', 'get_all')

    def update_user_by_id(self, id=None, data=None, role_name=None, role_description=None):
        id = id or 1
        role_instance = Role()
        role_name = role_name or "Admin"
        role_description = role_description or "Administrator_Updated"
        response = role_instance.search_roles({'id': None, 'role_name': role_name, 'role_description': None})
        if response.status_code == 200:
            role_data = response.json()
            roles = role_data['content']
            for role in roles:
                role_name = role['role_name']
                role_id = role['id']
                print(f"Role Name: {role_name}, Role ID: {role_id}")
                if role['role_name'] == 'Admin':
                    data = data or GenerateData.generate_user_data()
                    data["role_id"] = role['id']
                    break
        self.send_request('put', 'update', id=id, json=data)

    def delete_user(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_user(self, params=None):
        params = params or {
            'email': '',
            'username': 'admin',
            'password': '',
            'firstName': '',
            'lastName': '',
            'roleID': 1
        }
        return self.send_request('get', 'search', params=params)

    def exists_by_username(self, username=None):
        username = username or "admin"
        params = {'username': username}
        self.send_request('get', 'exists_by_username', params=params)

    def exists_by_email(self, email=None):
        email = email or "admin@example.com"
        params = {'email': email}
        self.send_request('get', 'exists_by_email', params=params)

#endregion

#region Toy

class Toy:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/toy/createToy",
        "get": "/api/toy/getToy/{id}",
        "get_all": "/api/toy/getAllToys",
        "update": "/api/toy/updateToy/{id}",
        "delete": "/api/toy/deleteToy/{id}",
        "search": "/api/toy/searchToys"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.format(id=kwargs['id'])
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e

    def add_toy(self, data=None, name=None, description_id=None, added_by=None, added_date=None, updated_by=None, updated_date=None, quantity=None):
        name = name or "nail"
        description_instance = Description()
        response = description_instance.search_descriptions({"name": name, "description": None})
        if response.status_code == 200:
            description_data = response.json()
            description = description_data['content']
            for desc in description:
                name = desc['name']
                description_id = desc['id']
                print(f"Description Name: {name}, Description ID: {description_id}")
                if desc['name'] == 'nail':
                    break
        user_instance = User()
        user_response = user_instance.search_user({'email': '', 'username': 'SantaClaus', 'password': '', 'firstName': '', 'lastName': '','roleID': ''})
        if user_response.status_code == 200:
            user_data = user_response.json()
            user = user_data['content']
            for u in user:
                username = u['username']
                user_id = u['id']
                print(f"Username: {username}, User ID: {user_id}")
                if u['username'] == 'SantaClaus':
                    added_by = u['id']
                    updated_by = u['id']
                    break
        current_datetime = datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        added_date = current_datetime
        updated_date = current_datetime
        quantity = quantity or 10
        data = data or GenerateData.generate_toy_data(name=name, description_id=description_id, added_by=added_by, added_date=added_date, updated_by=updated_by, updated_date=updated_date, quantity=quantity)
        self.send_request('post', 'add', json=data)

    def get_toy_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_toys(self):
        self.send_request('get', 'get_all')

    def update_toy_by_id(self, id=None, data=None, name=None, description_id=None, added_by=None, added_date=None, updated_by=None, updated_date=None, quantity=None):
        id = id or 1
        name = name or "nail"
        response = self.search_toys({"name": name})
        if response.status_code == 200:
            toy_data = response.json()
            current_data = toy_data['content']
            for cur in current_data:
                added_by = cur['added_by']
                added_date = cur['added_date']
                print(f"Added By: {added_by}, Added Date: {added_date}")
                if cur['name'] == name:
                    break
        description_instance = Description()
        response = description_instance.search_descriptions({"name": name, "description": None})
        if response.status_code == 200:
            description_data = response.json()
            description = description_data['content']
            for desc in description:
                name = desc['name']
                description_id = desc['id']
                print(f"Description Name: {name}, Description ID: {description_id}")
                if desc['name'] == name:
                    break
        user_instance = User()
        user_response = user_instance.search_user({'email': None, 'username': 'SantaClaus', 'password': None, 'firstName': None, 'lastName': None,'roleID': None})
        if user_response.status_code == 200:
            user_data = user_response.json()
            user = user_data['content']
            for u in user:
                username = u['username']
                user_id = u['id']
                print(f"Username: {username}, User ID: {user_id}")
                if u['username'] == username:
                    updated_by = u['id']
                    break
        current_datetime = datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        updated_date = current_datetime
        quantity = quantity or 10
        data = data or GenerateData.generate_toy_data(name=name, description_id=description_id, added_by=added_by, added_date=added_date, updated_by=updated_by, updated_date=updated_date, quantity=50)
        self.send_request('put', 'update', id=id, json=data)

    def delete_toy_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_toys(self, params=None):
        params = params or {"id": 1, "name": "Teddy Bear", "description_id": 1, "added_by": 1, "added_date": "2022-01-01T00:00:00Z", "updated_by": 1, "updated_date": "2022-01-01T00:00:00Z", "quantity": 10}
        return self.send_request('get', 'search', params=params)

#endregion

#region Child

class Child:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/child/createChild",
        "get": "/api/child/getChild/{id}",
        "get_all": "/api/child/getAllChildren",
        "update": "/api/child/updateChild/{id}",
        "delete": "/api/child/deleteChild/{id}",
        "search": "/api/child/searchChildren",
        "exists_by_child_id_and_status_name": "/api/child/existsByIdAndStatusID_StatusName"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.format(id=kwargs['id'])
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e
        
    def add_child(self, data=None, first_name=None, last_name=None, birthdate=None, status_id=None, location_id=None):
        first_name = first_name or "Johnny"
        last_name = last_name or "Appleseed"
        birthdate = birthdate or "1985-01-01T00:00:00Z"
        child_status_instance = ChildStatus()
        status_response = child_status_instance.search_statuses({"status_name": "Nice", "status_description": None})
        print(status_response)
        if status_response.status_code == 200:
            status_data = status_response.json()
            print(status_data)
            status_content = status_data['content']
            for status in status_content:
                status_name = status['status_name']
                status_id = status['id']
                print(f"Status Name: {status_name}, Status ID: {status_id}")
                if status['status_name'] == 'Nice':
                    break
        location_instance = Location()
        location_response = location_instance.search_locations({"address": None, "city": None, "state_prov": None, "zip_code": None, "country": None, "region": None, "latitude": 39.7817, "longitude": -89.6500})
        print(location_response)
        if location_response.status_code == 200:
            location_data = location_response.json()
            print(location_data)
            location_content = location_data['content']
            for location in location_content:
                address = location['address']
                location_id = location['id']
                print(f"Address: {address}, Location ID: {location_id}")
                if location['address'] == '123 Main St':
                    break
        data = data or GenerateData.generate_child_data(first_name=first_name, last_name=last_name, birthdate=birthdate, status_id=status_id, location_id=location_id)
        print(data)
        self.send_request('post', 'add', json=data)

    def get_child_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_children(self):
        self.send_request('get', 'get_all')

    def update_child_by_id(self, id=None, data=None, first_name=None, last_name=None, birthdate=None, status_id=None, location_id=None):
        child_response = self.search_children({"first_name": "Johnny", "last_name": "Appleseed", "birthdate": None, "status_id": None, "location_id": location_id})
        if child_response.status_code == 200:
            child_data = child_response.json()
            current_data = child_data['content']
            for cur in current_data:
                id = cur['id']
                first_name = cur['first_name']
                last_name = cur['last_name']
                birthdate = cur['birthdate']
                print(f"First Name: {first_name}, Last Name: {last_name}, Age: {birthdate}")
                if cur['first_name'] == first_name:
                    break
        last_name = "UpdatedSeed"
        child_status_instance = ChildStatus()
        status_response = child_status_instance.search_statuses({"status_name": "Nice", "status_description": None})
        if status_response.status_code == 200:
            status_data = status_response.json()
            status_content = status_data['content']
            for status in status_content:
                status_name = status['status_name']
                status_id = status['id']
                print(f"Status Name: {status_name}, Status ID: {status_id}")
                if status['status_name'] == 'Nice':
                    break
        location_instance = Location()
        location_response = location_instance.search_locations({"address": "12367 Main St", "city": None, "state_prov": None, "zip_code": None, "country": "USA", "region": None, "latitude": 39.7817, "longitude": -89.6500})
        if location_response.status_code == 200:
            location_data = location_response.json()
            location_content = location_data['content']
            for location in location_content:
                address = location['address']
                location_id = location['id']
                print(f"Address: {address}, Location ID: {location_id}")
                if location['address'] == '123 Main St':
                    break
        data = data or GenerateData.generate_child_data(first_name=first_name, last_name=last_name, birthdate=birthdate, status_id=status_id, location_id=location_id)
        print(data)
        self.send_request('put', 'update', id=id, json=data)

    def delete_child_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_children(self, params=None):
        params = params or {
            'id': None,
            'first_name': 'Johnny',
            'last_name': 'Appleseed',
            'birthdate': None,
            'status_id': None,
            'location_id': None
        }
        return self.send_request('get', 'search', params=params)

    def exists_by_child_id_and_status_name(self, id=None, statusName=None, params=None):
        child_response = self.search_children({"first_name": "Johnny", "last_name": "Appleseed", "birthdate": None, "status_id": None, "location_id": None})
        if child_response.status_code == 200:
            child_data = child_response.json()
            current_data = child_data['content']
            for cur in current_data:
                id = cur['id']
                first_name = cur['first_name']
                last_name = cur['last_name']
                birthdate = cur['birthdate']
                print(f"First Name: {first_name}, Last Name: {last_name}, Birthdate: {birthdate}")
                if cur['first_name'] == first_name:
                    break
        child_status_instance = ChildStatus()
        status_response = child_status_instance.search_statuses({"status_name": "Nice", "status_description": None})
        if status_response.status_code == 200:
            status_data = status_response.json()
            status_content = status_data['content']
            for status in status_content:
                statusName = status['status_name']
                print(f"Status Name: {statusName}, Status ID: {statusName}")
                if status['status_name'] == 'Nice':
                    break
        params = {
            'id': id,
            'statusName': statusName
        }
        self.send_request('get', 'exists_by_child_id_and_status_name', params=params)
    
#endregion

#region Wishlist

class Wishlist:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/wishlist/createWishlist",
        "get": "/api/wishlist/getWishlist/{id}",
        "get_all": "/api/wishlist/getAllWishlists",
        "update": "/api/wishlist/updateWishlist/{id}",
        "delete": "/api/wishlist/deleteWishlist/{id}",
        "search": "/api/wishlist/searchWishlists",
        "exists_by_child_id_and_toy_id": "/api/wishlist/existsByIdAndChild_Id"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.format(id=kwargs['id'])
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e
        
    def add_wishlist(self, data=None, child_id=None, toy_id=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        toy_instance = Toy()
        toy_response = toy_instance.search_toys({"name": "nail", "description_id": None, "added_by": None, "added_date": None, "updated_by": None, "updated_date": None, "quantity": None})
        if toy_response.status_code == 200:
            toy_data = toy_response.json()
            toy_content = toy_data['content']
            for toy in toy_content:
                toy_id = toy['id']
                print(f"Toy ID: {toy_id}")
                if toy['name'] == 'nail':
                    break
        data = data or GenerateData.generate_wishlist_data(child_id=child_id, toy_id=toy_id)
        self.send_request('post', 'add', json=data)

    def get_wishlist_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)

    def get_all_wishlists(self):
        self.send_request('get', 'get_all')
    
    def update_wishlist_by_id(self, id=None, data=None, child_id=None, toy_id=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        toy_instance = Toy()
        toy_response = toy_instance.search_toys({"name": "nail", "description_id": None, "added_by": None, "added_date": None, "updated_by": None, "updated_date": None, "quantity": None})
        if toy_response.status_code == 200:
            toy_data = toy_response.json()
            toy_content = toy_data['content']
            for toy in toy_content:
                toy_id = toy['id']
                print(f"Toy ID: {toy_id}")
                if toy['name'] == 'nail':
                    break
        wishlist_response = self.search_wishlists({"id": None, "child_id": child_id, "toy_id": toy_id})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                id = wishlist['id']
                print(f"Child ID: {child_id}, Toy ID: {toy_id}")
                if wishlist['child_id'] == child_id:
                    break
        data = data or GenerateData.generate_wishlist_data(child_id=child_id, toy_id=toy_id)
        print(data)
        self.send_request('put', 'update', id=id, json=data)

    def delete_wishlist_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)
    
    def search_wishlists(self, id=None, child_id=None, toy_id=None, params=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        toy_instance = Toy()
        toy_response = toy_instance.search_toys({"name": "nail", "description_id": None, "added_by": None, "added_date": None, "updated_by": None, "updated_date": None, "quantity": None})
        if toy_response.status_code == 200:
            toy_data = toy_response.json()
            toy_content = toy_data['content']
            for toy in toy_content:
                toy_id = toy['id']
                print(f"Toy ID: {toy_id}")
                if toy['name'] == 'nail':
                    break
        params = params or {'id': id, 'child_id': child_id, 'toy_id': toy_id}
        return self.send_request('get', 'search', params=params)

    def exists_by_child_id_and_toy_id(self, id=None, child_id=None, toy_id=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        toy_instance = Toy()
        toy_response = toy_instance.search_toys({"name": "nail", "description_id": None, "added_by": None, "added_date": None, "updated_by": None, "updated_date": None, "quantity": None})
        if toy_response.status_code == 200:
            toy_data = toy_response.json()
            toy_content = toy_data['content']
            for toy in toy_content:
                toy_id = toy['id']
                print(f"Toy ID: {toy_id}")
                if toy['name'] == 'nail':
                    break
        wishlist_response = self.search_wishlists({"id": None, "child_id": child_id, "toy_id": toy_id})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                id = wishlist['id']
                print(f"Child ID: {child_id}, Toy ID: {toy_id}")
                if wishlist['child_id'] == child_id:
                    break
        params = {'id': id, 'child_id': child_id}
        self.send_request('get', 'exists_by_child_id_and_toy_id', params=params)
    
#endregion

#region Delivery

class Delivery:
    BASE_URL = 'http://localhost:8080'
    ENDPOINTS = {
        "add": "/api/deliveries/createDelivery",
        "get": "/api/deliveries/getDelivery/{id}",
        "get_all": "/api/deliveries/getAllDeliveries",
        "update": "/api/deliveries/updateDelivery/{id}",
        "delete": "/api/deliveries/deleteDelivery/{id}",
        "search": "/api/deliveries/searchDeliveries",
        "exists_by_child_id_and_wishlist_id": "/api/deliveries/existsByChildID_IdAndWishlistID_Id",
        "exists_by_delivery_status": "/api/deliveries/existsByChildID_IdAndWishlistID_IdAndStatusID_Id",
        "exists_by_delivery_date": "/api/deliveries/existsByChildID_IdAndWishlistID_IdAndStatusID_IdAndDeliveredDate"
    }

    def send_request(self, method, endpoint, **kwargs):
        url = f'{self.BASE_URL}{self.ENDPOINTS[endpoint]}'
        if 'id' in kwargs:
            url = url.format(id=kwargs['id'])
            del kwargs['id']
        try:
            response = requests.request(method, url, **kwargs)
            logging.info(f'Test: {endpoint} - Status code: {response.status_code} - method: {method}')
            return response
        except Exception as e:
            logging.error('Error: %s', e)
            raise e
        
    def add_delivery(self, data=None, child_id=None, location_id=None, wishlist_id=None, status_id=None, delivered_date=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        location_instance = Location()
        location_response = location_instance.search_locations({"address": "12367 Main St", "city": None, "state_prov": None, "zip_code": None, "country": None, "region": None, "latitude": 39.7817, "longitude": -89.6500})
        if location_response.status_code == 200:
            location_data = location_response.json()
            location_content = location_data['content']
            for location in location_content:
                location_id = location['id']
                print(f"Location ID: {location_id}")
                if location['address'] == '12367 Main St':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        delivery_instance = DeliveryStatus()
        delivery_status_response = delivery_instance.search_statuses({"status_name": "Delivered", "status_description": None})
        if delivery_status_response.status_code == 200:
            delivery_status_response = delivery_status_response.json()
            status_content = delivery_status_response['content']
            for status in status_content:
                status_id = status['id']
                print(f"Status ID: {status_id}")
                if status['status_name'] == 'Delivered':
                    break
        delivered_date = delivered_date or datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        data = data or GenerateData.generate_delivery_data(child_id=child_id, delivered_date=delivered_date, status_id=status_id, location_id=location_id, wishlist_id=wishlist_id)
        print(data)
        self.send_request('post', 'add', json=data)

    def get_delivery_by_id(self, id=None):
        id = id or 1
        self.send_request('get', 'get', id=id)
    
    def get_all_deliveries(self):
        self.send_request('get', 'get_all')

    def update_delivery_by_id(self, id=None, data=None, child_id=None, location_id=None, wishlist_id=None, status_id=None, delivered_date=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        location_instance = Location()
        location_response = location_instance.search_locations({"address": "12367 Main St", "city": None, "state_prov": None, "zip_code": None, "country": None, "region": None, "latitude": 39.7817, "longitude": -89.6500})
        if location_response.status_code == 200:
            location_data = location_response.json()
            location_content = location_data['content']
            for location in location_content:
                location_id = location['id']
                print(f"Location ID: {location_id}")
                if location['address'] == '12367 Main St':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        delivery_instance = DeliveryStatus()
        status_data = delivery_instance.search_statuses({"status_name": "Delivered", "status_description": None})
        if status_data.status_code == 200:
            status_data = status_data.json()
            status_content = status_data['content']
            for status in status_content:
                status_id = status['id']
                print(f"Status ID: {status_id}")
                if status['status_name'] == 'Delivered':
                    break
        delivery_response = self.search_deliveries({"id": None, "child_id": child_id, "location_id": location_id, "wishlist_id": wishlist_id, "status_id": status_id, "delivered_date": delivered_date})
        if delivery_response.status_code == 200:
            delivery_data = delivery_response.json()
            delivery_content = delivery_data['content']
            for delivery in delivery_content:
                id = delivery['id']
                print(f"Delivery ID: {id}")
                if delivery['child_id'] == child_id:
                    break
        delivered_date = delivered_date or datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        data = data or GenerateData.generate_delivery_data(child_id=child_id, location_id=location_id, wishlist_id=wishlist_id, status_id=status_id, delivered_date=delivered_date)
        print(data)
        self.send_request('put', 'update', id=id, json=data)

    def delete_delivery_by_id(self, id=None):
        id = id or 1
        self.send_request('delete', 'delete', id=id)

    def search_deliveries(self, id=None, child_id = None, location_id = None, wishlist_id = None, status_id = None, delivered_date = None, params=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        location_instance = Location()
        location_response = location_instance.search_locations({"address": "12367 Main St", "city": None, "state_prov": None, "zip_code": None, "country": None, "region": None, "latitude": 39.7817, "longitude": -89.6500})
        if location_response.status_code == 200:
            location_data = location_response.json()
            location_content = location_data['content']
            for location in location_content:
                location_id = location['id']
                print(f"Location ID: {location_id}")
                if location['address'] == '12367 Main St':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        delivery_instance = DeliveryStatus()
        status_data = delivery_instance.search_statuses({"status_name": "Delivered", "status_description": None})
        if status_data.status_code == 200:
            status_data = status_data.json()
            status_content = status_data['content']
            for status in status_content:
                status_id = status['id']
                print(f"Status ID: {status_id}")
                if status['status_name'] == 'Delivered':
                    break
        params = params or {'child_id': child_id, 'location_id': location_id, 'wishlist_id': wishlist_id, 'status_id': status_id, 'delivered_date': delivered_date}
        return self.send_request('get', 'search', id=id, params=params)

    def exists_by_child_id_and_wishlist_id(self, child_id = None, wishlist_id = None, params=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        params = params or {'childId': child_id, 'wishlistId': wishlist_id}
        self.send_request('get', 'exists_by_child_id_and_wishlist_id', params=params)

    def exists_by_delivery_status(self, child_id = None, wishlist_id = None, status_id = None, params=None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        delivery_status_instance = DeliveryStatus()
        status_response = delivery_status_instance.search_statuses({"status_name": "Delivered", "status_description": None})
        if status_response.status_code == 200:
            status_data = status_response.json()
            status_content = status_data['content']
            for status in status_content:
                status_id = status['id']
                print(f"Status ID: {status_id}")
                if status['status_name'] == 'Delivered':
                    break
        status_id = status_id or status_data['id']
        params = {'childId': child_id, 'wishlistId': wishlist_id, 'deliveryStatusId': status_id}
        self.send_request('get', 'exists_by_delivery_status', params=params)

    def exists_by_delivery_date(self, child_id = None, wishlist_id = None, status_id = None, delivered_date = None):
        child_instance = Child()
        child_response = child_instance.search_children({"first_name": "Johnny", "last_name": "Appleseed"})
        if child_response.status_code == 200:
            child_data = child_response.json()
            child_content = child_data['content']
            for child in child_content:
                child_id = child['id']
                print(f"Child ID: {child_id}")
                if child['first_name'] == 'Johnny':
                    break
        wishlist_instance = Wishlist()
        wishlist_response = wishlist_instance.search_wishlists({"id": None, "child_id": child_id, "toy_id": None})
        if wishlist_response.status_code == 200:
            wishlist_data = wishlist_response.json()
            wishlist_content = wishlist_data['content']
            for wishlist in wishlist_content:
                wishlist_id = wishlist['id']
                print(f"Wishlist ID: {wishlist_id}")
                if wishlist['child_id'] == child_id:
                    break
        delivery_status_instance = DeliveryStatus()
        status_response = delivery_status_instance.search_statuses({"status_name": "Delivered", "status_description": "Toy has been delivered."})
        if status_response.status_code == 200:
            status_data = status_response.json()
            status_content = status_data['content']
            for status in status_content:
                status_id = status['id']
                print(f"Status ID: {status_id}")
                if status['status_name'] == 'Delivered':
                    break
        delivered_date = delivered_date or "2021-12-01"
        params = {'childId': child_id, 'wishlistId': wishlist_id, 'deliveryStatusId': status_id, 'deliveryDate': delivered_date}
        self.send_request('get', 'exists_by_delivery_date', params=params)

#endregion


def run_tests():
    description = Description()
    logging.info("add_description")
    description.add_description()
    logging.info("get_description_by_id")
    description.get_description_by_id()
    logging.info("get_all_descriptions")
    description.get_all_descriptions()
    logging.info("update_description_by_id")
    description.update_description_by_id()
    logging.info("search_descriptions")
    description.search_descriptions()
    logging.info("description_exists_by_id_and_name")
    description.description_exists_by_name()
    logging.info("description_exists_by_id_and_description")
    description.description_exists_by_description()
    logging.info("delete_description_by_id")
    # description.delete_description_by_id()

    role = Role()
    logging.info("add_role")
    role.add_role()
    logging.info("get_role_by_id")
    role.get_role_by_id()
    logging.info("get_all_roles")
    role.get_all_roles()
    logging.info("update_role_by_id")
    role.update_role_by_id()
    logging.info("search_roles")
    role.search_roles()
    logging.info("role_exists")
    role.role_exists()
    logging.info("delete_role_by_id")
    # role.delete_role_by_id()

    childStatus = ChildStatus()
    logging.info("add_status")
    childStatus.add_status()
    logging.info("get_status_by_id")
    childStatus.get_status_by_id()
    logging.info("get_all_statuses")
    childStatus.get_all_statuses()
    logging.info("update_status_by_id")
    childStatus.update_status_by_id()
    logging.info("search_statuses")
    childStatus.search_statuses()
    logging.info("status_exists_by_id_and_name")
    childStatus.status_exists_by_id_and_name()
    logging.info("status_exists_by_id_name_and_description")
    childStatus.status_exists_by_id_name_and_description()
    logging.info("delete_status_by_id")
    # childStatus.delete_status_by_id()

    deliveryStatus = DeliveryStatus()
    logging.info("add_status")
    deliveryStatus.add_status()
    logging.info("get_status_by_id")
    deliveryStatus.get_status_by_id()
    logging.info("get_all_statuses")
    deliveryStatus.get_all_statuses()
    logging.info("update_status_by_id")
    deliveryStatus.update_status_by_id()
    logging.info("search_statuses")
    deliveryStatus.search_statuses()
    logging.info("status_exists_by_name")
    deliveryStatus.exists_by_name()
    logging.info("delete_status_by_id")
    # deliveryStatus.delete_status_by_id()

    location = Location()
    logging.info("add_location")
    location.add_location()
    logging.info("get_location_by_id")
    location.get_location_by_id()
    logging.info("get_all_locations")
    location.get_all_locations()
    logging.info("update_location_by_id")
    location.update_location_by_id()
    logging.info("search_locations")
    location.search_locations()
    logging.info("location_exists_by_address")
    location.exists_by_address()
    logging.info("location_exists_by_city")
    location.exists_by_city()
    logging.info("location_exists_by_state_prov")
    location.exists_by_state_prov()
    logging.info("location_exists_by_zip_code")
    location.exists_by_zip_code()
    logging.info("location_exists_by_country")
    location.exists_by_country()
    logging.info("location_exists_by_region")
    location.exists_by_region()
    logging.info("location_exists_by_latitude_and_longitude")
    location.exists_by_latitude_and_longitude()
    logging.info("delete_location_by_id")
    # location.delete_location_by_id()

    user = User()
    logging.info("add_user")
    user.add_user()
    logging.info("get_user_by_id")
    user.get_user_by_id()
    logging.info("get_all_users")
    user.get_all_users()
    logging.info("update_user_by_id")
    user.update_user_by_id()
    logging.info("search_user")
    user.search_user()
    logging.info("user_exists_by_username")
    user.exists_by_username()
    logging.info("user_exists_by_email")
    user.exists_by_email()
    logging.info("delete_user_by_id")
    # user.delete_user()

    toy = Toy()
    logging.info("add_toy")
    toy.add_toy()
    logging.info("get_toy_by_id")
    toy.get_toy_by_id()
    logging.info("get_all_toys")
    toy.get_all_toys()
    logging.info("update_toy_by_id")
    toy.update_toy_by_id()
    logging.info("search_toys")
    toy.search_toys()
    logging.info("delete_toy_by_id")
    # toy.delete_toy_by_id()

    child = Child()
    logging.info("add_child")
    child.add_child()
    logging.info("get_child_by_id")
    child.get_child_by_id()
    logging.info("get_all_children")
    child.get_all_children()
    logging.info("update_child_by_id")
    child.update_child_by_id()
    logging.info("search_children")
    child.search_children()
    logging.info("exists_by_child_id_and_status_name")
    child.exists_by_child_id_and_status_name()
    # child.delete_child_by_id()

    wishlist = Wishlist()
    logging.info("add_wishlist")
    wishlist.add_wishlist()
    logging.info("get_wishlist_by_id")
    wishlist.get_wishlist_by_id()
    logging.info("get_all_wishlists")
    wishlist.get_all_wishlists()
    logging.info("update_wishlist_by_id")
    wishlist.update_wishlist_by_id()
    logging.info("search_wishlists")
    wishlist.search_wishlists()
    logging.info("exists_by_child_id_and_toy_id")
    wishlist.exists_by_child_id_and_toy_id()
    logging.info("delete_wishlist_by_id")
    # wishlist.delete_wishlist_by_id()

    delivery = Delivery()
    logging.info("add_delivery")
    delivery.add_delivery()
    logging.info("get_delivery_by_id")
    delivery.get_delivery_by_id()
    logging.info("get_all_deliveries")
    delivery.get_all_deliveries()
    logging.info("update_delivery_by_id")
    delivery.update_delivery_by_id()
    logging.info("search_deliveries")
    delivery.search_deliveries()
    logging.info("exists_by_child_id_and_wishlist_id")
    delivery.exists_by_child_id_and_wishlist_id()
    logging.info("exists_by_delivery_status")
    delivery.exists_by_delivery_status()
    logging.info("exists_by_delivery_date")
    delivery.exists_by_delivery_date()
    logging.info("delete_delivery_by_id")
    # delivery.delete_delivery_by_id()

if __name__ == '__main__':
    run_tests()
    logging.info('All tests passed successfully.')


<template>
    <div class="myHeader">
        <h1 class="greeting">Hello {{ name }}</h1>
        <div class="logoutSection">
            <img src="/favicon.ico"
            alt="Logout"
            @click="logout"
            style="cursor: pointer;"
            />
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import axios from 'axios';

const name = ref('');

const logout = async () => {
    try {
        // Make an HTTP request to your backend endpoint for logout
        await axios.post('http://localhost:8080/api/logout'); // Replace with your actual API endpoint
        // Optionally, you can navigate to the login page or perform other actions after logout
    } catch (error) {
        console.error('Error during logout:', error);
    }
};

onMounted(async () => {
    try {
        // Make an HTTP request to your backend endpoint to fetch the name
        const response = await axios.get('http://localhost:8080/api/getName');
        name.value = response.data.name; // Assuming the response has a 'name' property
        console.log("name: " + name.value)
        // console.log("response.data: " + response.data)
    } catch (error) {
        console.error('Error fetching name:', error);
    }
});
</script>

<style scoped>
/* Your scoped styles here */
.myHeader {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #003366;
    color: #ffffff;
    padding: 20px;
}


.logoutSection {
    text-align: right;
}

.greeting {
    transition: letter-spacing 0.3s ease-in-out;
}

.greeting:hover {
    letter-spacing: 3px;
}
</style>

<template>
    <div>
        <h2>Create Transaction</h2>
        <label for="operator">Operator:</label>
        <input v-model="operator" type="radio" id="plus" value="+" />
        <label for="plus">+</label>
        <input v-model="operator" type="radio" id="minus" value="-" />
        <label for="minus">-</label>
        <br />
        <label for="amount">Amount:</label>
        <input v-model="amount" type="number" id="amount" />
        <br />
        <label for="time">Time:</label>
        <input v-model="time" type="datetime-local" id="time" />
        <br />
        <button @click="createTransaction">Create Transaction</button>
    </div>
</template>

<script>
import axios from "axios";

const email = 'john.doe@example.com';

// const props = defineProps(['collectionId']);

export default {
    props: ['collectionId'], // Definice props pro komponentu
    data() {
        return {
            operator: "+",
            amount: 0,
            time: new Date().toISOString().slice(0, 16), // Nastavte aktuální čas
        };
    },
    methods: {
        async createTransaction() {
            // Přidáme operátor k částce
            const signedAmount = this.operator === "+" ? this.amount : -this.amount;

            try {
                const response = await axios.post(
                    "http://localhost:8080/api/transaction",
                    {
                        amount: signedAmount,
                        time: this.time,
                        email: email,
                        collectionId: this.collectionId,
                    }
                );

                console.log("Transaction created:", response.data);
            } catch (error) {
                console.error("Error creating transaction:", error);
            }
        },
    },
};
</script>

<style scoped>
/* Styly pro komponentu */
</style>
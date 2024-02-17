<template>
    <div>
        <button @click="showAddTileForm">+</button>
        <div v-if="showForm">
            <form @submit.prevent="addTile">
                <label for="newTileId">Tile ID:</label>
                <input v-model="newTileId" type="number" id="newTileId" required />
                <br />
                <label for="newTileLeft">Left:</label>
                <input v-model="newTileLeft" type="number" id="newTileLeft" required />
                <br />
                <label for="newTileTop">Top:</label>
                <input v-model="newTileTop" type="number" id="newTileTop" required />
                <br />
                <label for="newTileColor">Color:</label>
                <input v-model="newTileColor" type="color" id="newTileColor" />
                <br />
                <button type="submit">Add Tile</button>
            </form>
        </div>
        <div class="dashboard">
            <tile-component
                v-for="(tile, index) in tiles"
                :key="index"
                :tile="tile"
                @dragStart="onDragStart"
                @dragEnd="onDragEnd"
            ></tile-component>
        </div>
    </div>
</template>

<script>
import TileComponent from "@/components/NewTileComponent.vue";

export default {
    components: {
        TileComponent,
    },
    data() {
        return {
            tiles: [
                { id: 1, left: 10, top: 10, color: "#ff0000" },
                { id: 2, left: 120, top: 10, color: "#00ff00" },
                { id: 3, left: 230, top: 10, color: "#0000ff" },
            ],
            draggingTile: null,
            showForm: false,
            newTileId: 4,
            newTileLeft: 10,
            newTileTop: 10,
            newTileColor: "#000000",
        };
    },
    methods: {
        onDragStart(tile) {
            this.draggingTile = tile;
        },
        onDragEnd() {
            this.draggingTile = null;
        },
        showAddTileForm() {
            this.showForm = true;
        },
        addTile() {
            const newTile = {
                id: this.newTileId,
                left: this.newTileLeft,
                top: this.newTileTop,
                color: this.newTileColor,
            };

            this.tiles.push(newTile);
            this.showForm = false;
            this.newTileId += 1;
            this.newTileLeft = 10;
            this.newTileTop = 10;
            this.newTileColor = "#000000";
        },
    },
    computed: {
        dashboardStyles() {
            return {
                position: "relative",
                width: "100%",
                height: "400px", // Adjust as needed
                background: "#f0f0f0", // Dashboard background color
            };
        },
    },
};
</script>

<style scoped>
.dashboard {
    position: relative;
    width: 100%;
    height: 400px; /* Adjust as needed */
    background: #f0f0f0; /* Dashboard background color */
}
</style>

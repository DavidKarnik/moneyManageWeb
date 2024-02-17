<template>
    <div
        class="tile"
        :style="{ left: tile.left + 'px', top: tile.top + 'px', backgroundColor: tile.color }"
        @mousedown="startDrag"
    >
        {{ tile.id }}
    </div>
</template>

<script>
export default {
    props: {
        tile: Object,
    },
    methods: {
        startDrag(event) {
            this.$emit("dragStart", this.tile);
            const initialX = event.clientX - this.tile.left;
            const initialY = event.clientY - this.tile.top;

            const moveHandler = (event) => {
                this.tile.left = event.clientX - initialX;
                this.tile.top = event.clientY - initialY;
            };

            const upHandler = () => {
                this.$emit("dragEnd");
                window.removeEventListener("mousemove", moveHandler);
                window.removeEventListener("mouseup", upHandler);
            };

            window.addEventListener("mousemove", moveHandler);
            window.addEventListener("mouseup", upHandler);
        },
    },
};
</script>

<style scoped>
.tile {
    position: absolute;
    width: 100px;
    height: 100px;
    border: 1px solid #cccccc;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: grab;
}
</style>

<template>
  <div id="main">
    <div id="content">
      <h2>Ну тут без стакана не <br> разберешься</h2>
      <h5>Находите задачи и придумывайте решения</h5>
      <div id="search">
        <input :placeholder="'Введите название задачи'">
        <button>Поиск</button>
      </div>
    </div>

  </div>

</template>

<script setup>

</script>

<style lang="scss" scoped>
#main {
  position: fixed;
  z-index: 100;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("@/assets/bg.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: opacity 0.3s ease;
}

h2 {
  font-size: 60px;
  line-height: 64px;
}

h2, h5 {
  font-weight: 400;
  text-align: center;
  color: white;
}

h5 {
  margin-top: 32px;
  font-size: 18px;
}

#content {

  display: flex;
  flex-direction: column;
}

#search {
  margin-top: 50px;
  border-radius: 100px;
  display: flex;
  justify-content: space-between;
  width: 100%;
  background-color: #fff;

  & input {
    width: 80%;
    margin: 15px 31px;
    outline: none;
    border: none;
    font-size: 16px;
    line-height: 22px;

    &::placeholder {
      opacity: .4;
    }


  }

  button {
    cursor: pointer;
    border: none;
    border-radius: 100px;
    background-color: var(--color-main);
    color: black;
    font-size: 18px;
    font-weight: 500;
    padding: 18px 54px;

    &:hover {
      opacity: .7;
    }
  }
}

</style>
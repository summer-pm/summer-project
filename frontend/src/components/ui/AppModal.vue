<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div v-if="errorMessage" class="modal-error-message">
            {{ errorMessage }}
          </div>
          <div class="modal-close">
            <svg fill="none" height="40" viewBox="0 0 40 40" width="40" xmlns="http://www.w3.org/2000/svg"
                 @click="$emit('close')">
              <path
                  d="M22.357 20L30 27.643C30.6508 28.2938 30.6508 29.3491 30 30C29.3491 30.6509 28.2938 30.6509 27.6429 30L18.3501 20.7071C17.9595 20.3166 17.9595 19.6834 18.3501 19.2929L27.6429 9.99998C28.2938 9.34911 29.3491 9.34911 30 9.99998C30.6508 10.6509 30.6508 11.7061 30 12.357L22.357 20Z"
                  fill="white"/>
              <path
                  d="M18.1312 20L10.4882 27.643C9.83732 28.2938 9.83732 29.3491 10.4882 30C11.1391 30.6509 12.1943 30.6509 12.8452 30L22.1381 20.7071C22.5286 20.3166 22.5286 19.6834 22.1381 19.2929L12.8452 9.99998C12.1943 9.34911 11.1391 9.34911 10.4882 9.99998C9.83732 10.6509 9.83732 11.7061 10.4882 12.357L18.1312 20Z"
                  fill="white"/>
            </svg>
          </div>
          <div class="modal-content">
            <div class="modal-header">
              <slot name="header">
                default header
              </slot>
            </div>

            <div class="modal-body">
              <slot name="body">
                default body
              </slot>
            </div>
          </div>


        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "AppModal",
  props: {
    errorMessage: {
      type: String
    }
  }
}
</script>

<style lang="scss" scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-header {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 25px;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  margin: 0 auto;
  max-width: 460px;

  transition: all 0.3s ease;
}

.modal-close {
  display: flex;
  justify-content: end;

  & svg {
    cursor: pointer;

    &:hover {
      opacity: .7;
    }
  }
}

.modal-content {
  margin: 0 auto;

  max-width: 400px;
  padding: 20px 30px;
  border-radius: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  background-color: var(--color-background-soft);
}

.modal-body {

}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}

.modal-error-message {
  padding: 10px;
  text-align: center;
  color: var(--color--text-error);
  background-color: var(--color-background-error);
  border-color: var(--color--text-error);
  animation: shake 0.82s cubic-bezier(.36, .07, .19, .97) both;
  transform: translate3d(0, 0, 0);
}

@keyframes shake {
  10%, 90% {
    transform: translate3d(-1px, 0, 0);
  }

  20%, 80% {
    transform: translate3d(2px, 0, 0);
  }

  30%, 50%, 70% {
    transform: translate3d(-4px, 0, 0);
  }

  40%, 60% {
    transform: translate3d(4px, 0, 0);
  }
}
</style>
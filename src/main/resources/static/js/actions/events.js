export function eventListener(trigger, action, target) {
  const element = document.getElementById(target);
  element.addEventListener(trigger, action);
} 

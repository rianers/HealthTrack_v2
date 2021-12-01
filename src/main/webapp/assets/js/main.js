function openMenu() {
    const chevron = document.getElementById("chevron");
    chevron.classList.toggle("rotate180");
    const navbarContent = document.getElementById("navbar-content");
    navbarContent.classList.toggle("open");
  }

function addMenuItemFocus(elementId){
	document.getElementById(elementId).classList.add("navbar-item-active");
}

function changeJustifyContent(){
	document.getElementById("custom-navbar").classList.add("justify-content-end-important");
}

function formatIMC(number) {
	return number.toFixed(2);
} 
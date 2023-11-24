function scrollToSection(sectionId) {
    var section = document.getElementById(sectionId);
    if (section) {
        var menuItems = document.querySelectorAll('.header-title span');
        menuItems.forEach(function (item) {
            item.classList.remove('active');
        });

        section.scrollIntoView({ behavior: 'smooth' });

        var clickedItem = document.querySelector('[onclick="scrollToSection(\'' + sectionId + '\')"]');
        if (clickedItem) {
            clickedItem.classList.add('active');
        }
    }
}

document.querySelectorAll(".point").forEach(function (point, index) {
    point.addEventListener("click", function () {
        showCourse(index + 1);
    });
});

var currentBox = 1;
function changeContent() {
    var totalBoxes = document.querySelectorAll(".slide").length;
    var nextBox = currentBox % totalBoxes + 1;

    showCourse(nextBox);

    currentBox = nextBox;
}

setInterval(changeContent, 5000);

function showCourse(boxNumber) {
    var courses = document.querySelectorAll(".slide");
    var points = document.querySelectorAll(".point");
    
    courses.forEach(function(course, index) {
        course.style.display = index + 1 === boxNumber ? "flex" : "none";
    });

    points.forEach(function (point, index) {
        point.classList.toggle('active', index + 1 === boxNumber);
    });
}


showCourse(1);

// function scrollToSecondMain() {
//     document.getElementById('second-main').scrollIntoView({ behavior: 'smooth' });
// }
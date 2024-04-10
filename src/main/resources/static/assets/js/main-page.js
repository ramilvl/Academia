const coursesList = document.querySelector('.courses-list');

const coursesWrapper = document.querySelector('.courses-wrapper'); // make sure this is the correct class for the wrapper

document.querySelector('.icon-left').addEventListener('click', function() {
    navigateCourses(-1);
});

document.querySelector('.icon-right').addEventListener('click', function() {
    navigateCourses(1);
});

let currentCourseIndex = 0; // This will keep track of the currently displayed course

function navigateCourses(direction) {
    const courseSlide = document.querySelector('.course-slide');
    const courseWidth = courseSlide.offsetWidth; // This now includes the width of one course plus its margins
    const courseStyle = window.getComputedStyle(courseSlide);
    const courseMargin = parseFloat(courseStyle.marginLeft) + parseFloat(courseStyle.marginRight);
    const totalCourses = document.querySelectorAll('.course-slide').length;

    currentCourseIndex += direction;

    // Clamp the current index between 0 and totalCourses - number of visible slides
    if (currentCourseIndex < 0) {
        currentCourseIndex = 0;
    } else if (currentCourseIndex > totalCourses - Math.ceil(100 / parseInt(courseSlide.style.flexBasis))) {
        currentCourseIndex = totalCourses - Math.ceil(100 / parseInt(courseSlide.style.flexBasis));
    }

    const newTransformValue = -(courseWidth + courseMargin) * currentCourseIndex;
    coursesWrapper.style.transform = `translateX(${newTransformValue}px)`;
}




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

setInterval(changeContent, 7000);

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
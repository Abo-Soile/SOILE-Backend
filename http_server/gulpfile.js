
var gulp = require("gulp"),//http://gulpjs.com/
  util = require("gulp-util"),//https://github.com/gulpjs/gulp-util
  sass = require("gulp-sass"),//https://www.npmjs.org/package/gulp-sass
  rename = require('gulp-rename'),//https://www.npmjs.org/package/gulp-rename
  log = util.log,
  concat = require('gulp-concat');

var sassFiles = "src/main/resources/sass/*.scss";
var cssDest = "src/main/resources/static_files/css";

var cssName = "styles.css";

var gulp = require('gulp');
 
gulp.task('default', ["sass"],function() {
  // place code for your default task here
});

gulp.task("sass", function(){ 
  log("Generate CSS files " + (new Date()).toString());
    gulp.src(sassFiles)
    .pipe(sass({ style: 'expanded' }))
    //.pipe(gulp.dest(cssDest))
    .pipe(concat('styles.css'))
    //.pipe(rename("sassStyle.css"))
    .pipe(gulp.dest(cssDest));
});
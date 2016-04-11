var gulp = require("gulp"),//http://gulpjs.com/
  util = require("gulp-util"),//https://github.com/gulpjs/gulp-util
  sass = require("gulp-sass"),//https://www.npmjs.org/package/gulp-sass
  rename = require('gulp-rename'),//https://www.npmjs.org/package/gulp-rename
  log = util.log,
  concat = require('gulp-concat'),
  install = require("gulp-install");


var sassFiles = "src/main/resources/sass/*.scss";
var cssDest = "src/main/resources/static_files/css";

var cssName = "styles.css";

var gulp = require('gulp');
var Server = require('karma').Server;
 
gulp.task('default', ["sass"],function() {
  // place code for your default task here
});

gulp.task("install", function() {
  gulp.src(['./bower.json', './src/main/resources/package.json'])
  .pipe(install());
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

gulp.task("test", function(done) {
  new Server({
    configFile: __dirname + '/karma.conf.js',
    singleRun: true
  }, done).start();
})

gulp.task("testserver", function(done) {
  new Server({
    configFile: __dirname + '/karma.conf.js',
    singleRun: false
  }, done).start();
})


gulp.task('watch', function() {
  gulp.watch(sassFiles, ['sass']);
});
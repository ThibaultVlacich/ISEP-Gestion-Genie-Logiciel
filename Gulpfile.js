const gulp = require('gulp');
const sass = require('gulp-sass');
const cleanCSS = require('gulp-clean-css');
const imagemin = require('gulp-imagemin');

// Task for compiling SCSS files
gulp.task('compileSCSS', function () {
  gulp.src('./src/main/webapp/resources/scss/app.scss')
      .pipe(sass().on('error', sass.logError)) // Log compilation errors
      .pipe(cleanCSS()) // Minify output CSS
      .pipe(gulp.dest('./src/main/webapp/resources/core/css/'));
});

// Task for optimizing images
gulp.task('optimizeImages', function () {
  gulp.src('./src/main/webapp/resources/*/images/*', { base: './' })
      .pipe(imagemin())
      .pipe(gulp.dest('./'));
});

// Watch task
gulp.task('watch', ['optimizeImages'], function() {
  // Automatically recompile SCSS files when modified
  gulp.watch('./src/main/webapp/resources/scss/**/*.scss', ['compileSCSS']);
});

// Set default tasks
gulp.task('build', ['compileSCSS', 'optimizeImages']);
gulp.task('default', ['compileSCSS', 'optimizeImages']);

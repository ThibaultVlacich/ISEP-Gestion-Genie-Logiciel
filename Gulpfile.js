var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');

gulp.task('build', function() {
  gulp.src('./src/main/webapp/resources/scss/app.scss')
      .pipe(sass().on('error', sass.logError))
      .pipe(cleanCSS())
      .pipe(gulp.dest('./src/main/webapp/resources/core/css/'));
});

// Watch task
gulp.task('watch', function() {
  gulp.watch('./src/main/webapp/resources/scss/**/*.scss', ['build']);
});

// Set default task
gulp.task('default', ['watch']);

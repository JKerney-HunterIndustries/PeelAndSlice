let framerate;
describe('framerate', function() {
    beforeEach(function() {
        framerate = require('../app/quiz/framerate');
    });
    it('Dose Something', function() {
        framerate.getCreateCountdownFrames2('ntsc', null);
    });
});
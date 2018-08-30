let ntscFrameRate;
let tvType;

function getCreateCountdownFrames2(type, videoMaker) {
    let frames = 0;

    if ("ntsc" === type) {
        frames = tvType.getFrameRate();
    }

    for (let i = 0; i < frames; i++) {
        videoMaker.createFrame(i);
    }
}

function getCreateCountdownFrames(http, videoMaker) {
    const type = http.request.params["TvFormat"];
    getCreateCountdownFrames2(type, videoMaker)
}

function TvType(tvType) {
    this.BASE_FRAMERATE = 0;
    this.frameRate = ntscFrameRate;

    this.getFrameRate = function () {
        if (tvType === 'ntsc') {
            return this.frameRate + this.BASE_FRAMERATE;
        } else {
            return -1;
        }
    }
}

tvType = new TvType();
ntscFrameRate = 33;

module.exports = {
    createCountdownFrames: getCreateCountdownFrames,
    getCreateCountdownFrames2
};
from PIL import Image
import csv

# *Mandatory* load a photo
im = Image.open("C:\\xxx.png")

# convert a photo to RGB information
rgb_im = im.convert('RGB')
size = rgb_im.size

# *optional* create csv containing coordinates and RGB information
f = open('xxx.csv', 'w', newline='')
writer = csv.writer(f)

#loop
#x
for x in range(size[0]):
    #y
    for y in range(size[1]):
        # get RGB information pixel by pixel
        r,g,b = rgb_im.getpixel((x,y))

        # write coordinates and RGB information to csv
        writer.writerow([y, x, r, g, b])